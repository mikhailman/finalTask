package by.verishko.kefir.dao.connect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    public static final String DATABASE_PROPERTIES = "dbresourse.database";
    public static final String URL = "db.url";
    public static final String LOGIN = "db.login";
    public static final String PASSWORD = "db.password";
    public static final String CONNECTION_POOLSIZE = "db.poolsize";
    public static final String DB_DRIVER = "db.driver";

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
    private BlockingQueue<ProxyConnection> freeConnections;
    /**
     * Pool of using compounds with database.
     */
    private BlockingQueue<ProxyConnection> usedConnections;
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
     * default size of pool of connections to database.
     */
    private String driver;
    /**
     * driver for using database.
     */
    private int poolSize;

    /**
     * Constructor.
     */
    private ConnectionPool() {
        getProperties();
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
        init();
        checkPoolSize();
        usedConnections = new LinkedBlockingQueue<>();
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
    }

    /**
     * Initializing pool of connections to dataBase.
     */
    private void init() {
        freeConnections = new LinkedBlockingQueue<>(poolSize);
        ProxyConnection connection = null;
        for (int counter = 0; counter < poolSize; counter++) {
            try {
                connection = new ProxyConnection(DriverManager.getConnection(url, user, password));
                freeConnections.add(connection);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    private void checkPoolSize() {
        int poolRealSize = ((LinkedBlockingQueue) freeConnections).size();
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
    public Connection takeConnection() {
        ProxyConnection connection = null;
        while (connection == null) {
            try {
                connection = freeConnections.take();
                usedConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Утечка соединения" + e);
                Thread.currentThread().interrupt();
            }
        }
        return connection;
    }

    /**
     * Return connection to pool after use.
     *
     * @param connection using connection of pool.
     */
    public void releaseConnection(Connection connection) {
        if (connection instanceof ProxyConnection) {
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            usedConnections.remove(proxyConnection);
            freeConnections.offer(proxyConnection);
        }
    }

    /**
     * Clothing pool method which clothe all connections
     * and then deregister drivers.
     */
    public void closePool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                ProxyConnection proxyConnection = freeConnections.take();
                proxyConnection.realClose();
            } catch (SQLException | InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Cannot close connection.\n", e);
            }
        }
        logger.info("Connection pool successfully released");
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}
