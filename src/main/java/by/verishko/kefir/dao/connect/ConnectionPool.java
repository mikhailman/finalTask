package by.verishko.kefir.dao.connect;

import by.verishko.kefir.dao.exception.ConnectionError;
import by.verishko.kefir.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final String DATABASE_PROPERTIES = "dbresourse.database";
    private static final String URL = "db.url";
    private static final String LOGIN = "db.login";
    private static final String PASSWORD = "db.password";
    private static final String CONNECTION_POOLSIZE = "db.poolsize";
    private static final String DB_DRIVER = "db.driver";
    private static final String DB_MAX_SIZE = "db.max_size";
    private static final String DB_CHECK_CONNECTION_TIMEOUT = "db.check_connection_timeout";

    /**
     * static variable single_instance of type ConnectionPool.
     */
    private static ConnectionPool instance;
    /**
     * Identifier which shows was this class created before or no.
     * If it was initialised identifier has value true, otherwise
     * false.
     */
    private static AtomicBoolean instanceCreated = new AtomicBoolean(false);
    /**
     * A reentrant mutual exclusion Lock with the same basic
     * behavior and semantics as the implicit monitor lock
     * accessed using synchronized methods and statements,
     * but with extended capabilities.
     */
    private static ReentrantLock lock = new ReentrantLock();
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    /**
     * Pool of free compounds with database.
     */
    private BlockingDeque<ProxyConnection> freeConnections;
    /**
     * Pool of using compounds with database.
     */
    private BlockingDeque<ProxyConnection> usedConnections;
    /**
     * Value of database location.
     */
    private String url;
    /**
     * Login to database.
     */
    private String user;
    /**
     * Password to database.
     */
    private String password;
    /**
     * driver for using database.
     */
    private String driver;
    /**
     * default size of pool of connections to database.
     */
    private int poolSize;
    /**
     * maximal size of pool of connections to database.
     */
    private int maxSize;

    private int checkConnectionTimeout;

    /**
     * Constructor.
     */
    private ConnectionPool() {
        getProperties();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error(e);
            throw new ConnectionError(e);
        }
        init();
//        checkPoolSize();
    }

    /**
     * Method get properties to pool of connections from property file.
     */
    private void getProperties() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(DATABASE_PROPERTIES);
        url = resourceBundle.getString(URL);
        user = resourceBundle.getString(LOGIN);
        password = resourceBundle.getString(PASSWORD);
        poolSize = Integer.parseInt(resourceBundle.getString(CONNECTION_POOLSIZE).trim());
        driver = resourceBundle.getString(DB_DRIVER);
        maxSize = Integer.parseInt(resourceBundle.getString(DB_MAX_SIZE));
        checkConnectionTimeout = Integer.parseInt(resourceBundle.getString(DB_CHECK_CONNECTION_TIMEOUT));
    }

    /**
     * Initializing pool of connections to dataBase.
     */
    private void init() {
        freeConnections = new LinkedBlockingDeque<>(poolSize);
        usedConnections = new LinkedBlockingDeque<>();
        for (int counter = 0; counter < poolSize; counter++) {
            try {
                freeConnections.add(createConnection());
            } catch (SQLException e) {
                logger.error("Error created  pool. ", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    private void checkPoolSize() {
        int poolRealSize = freeConnections.size();
        if (poolRealSize == poolSize) {
            logger.debug("pool is correct");
        } else if (poolRealSize > 0) {
            logger.warn("Утечка соединения, created pool size: ", poolRealSize);
        } else {
            logger.error("no connection to DB");
            throw new ExceptionInInitializerError("no connection to DB");
        }
    }

    /**
     * Getting pool of connections.
     */
    private ProxyConnection createConnection() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(url, user, password));
    }

    /**
     * Access to single instance of type ConnectionPool.
     *
     * @return link to instance of class.
     */
    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /**
     * Take connection to database method.
     *
     * @return connection to database.
     */
    public Connection takeConnection() throws DAOException {
        ProxyConnection connection = null;
        while (connection == null) {
            try {
                if (!freeConnections.isEmpty()) {
                    connection = freeConnections.take();
                    if (!connection.isValid(checkConnectionTimeout)) {
                        connection.getConnection().close();
                        connection = null;
                    }
                } else if (usedConnections.size() < maxSize) {
                    connection = createConnection();
                } else {
                    logger.error("The limit of number of database connections is exceeded");
                    throw new DAOException();
                }
            } catch (InterruptedException | SQLException e) {
                logger.error("It is impossible to connect to a database", e);
                Thread.currentThread().interrupt();
                throw new DAOException(e);
            }
        }
        try {
            usedConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Утечка соединения" + e);
            logger.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Return connection to pool after use.
     *
     * @param connection using connection of pool.
     */
    void releaseConnection(ProxyConnection connection) {
        try {
            if (connection.isValid(checkConnectionTimeout)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnections.remove(connection);
                freeConnections.offer(connection);

                String message = String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size());
                logger.debug(message);
            }
        } catch (SQLException e1) {
            logger.warn("It is impossible to return database connection into pool", e1);
            try {
                connection.getConnection().close();
            } catch (SQLException e) {
                logger.warn("error closed connection.");
            }
        }
    }

    /**
     * Clothing pool method which clothe all connections
     * and then deregister drivers.
     */
    public void closePool() {
        ProxyConnection proxyConnection = null;
        for (int i = 0; i < freeConnections.size(); i++) {
            try {
                proxyConnection = freeConnections.take();
                proxyConnection.realClose();
            } catch (SQLException | InterruptedException e) {
                logger.error("Cannot close connection. ", e);
                Thread.currentThread().interrupt();
            }
        }
        logger.info("Connection pool successfully released");
        freeConnections.clear();
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Cant deregister SQL driver ", e);
            }
        }
    }
}
