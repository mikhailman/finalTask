package by.verishko.kefir.dao.impl;

import by.verishko.kefir.dao.Transaction;
import by.verishko.kefir.dao.TransactionFactory;
import by.verishko.kefir.dao.connect.ConnectionPool;
import by.verishko.kefir.dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionFactoryImpl implements TransactionFactory {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private Connection connection;

    public TransactionFactoryImpl() throws DAOException {
        connection = ConnectionPool.getInstance().takeConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error("It is impossible to turn off autocommiting for database connection", e);
            throw new DAOException(e);
        }
    }

    @Override
    public Transaction createTransaction() {
        return new TransactionImpl(connection);
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error("Error closed connection");
        }
    }
}
