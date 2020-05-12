package by.verishko.kefir.dao.impl;

import by.verishko.kefir.dao.DAO;
import by.verishko.kefir.dao.Transaction;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionImpl implements Transaction {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private Connection connection;

    TransactionImpl(final Connection connection) {
        this.connection = connection;
    }

    private BaseDao getDao(final TypeDao key) throws DAOException {
        BaseDao baseDao;
        switch (key) {
            case USER:
                baseDao = new UserDAOImpl();
                break;
            case PRODUCT:
                baseDao = new ProductDAOImpl();
                break;
            case CATEGORY:
                baseDao = new CategoryDAOImpl();
                break;
            case COMMENT:
                baseDao = new CommentDAOImpl();
                break;
            case LOCATION:
                baseDao = new LocationDAOImpl();
                break;
            case IMAGE:
                baseDao = new ImageDAOImpl();
                break;
            default:
                String message = "Incorrect type of DAO" + key;
                logger.error(message);
                throw new DAOException();
        }
        return baseDao;
    }

    @Override
    public <Type extends DAO<?>> Type createDao(TypeDao key) throws DAOException {
        BaseDao baseDao = getDao(key);
        baseDao.setConnection(connection);
        return (Type) baseDao;
    }

    @Override
    public void commit() throws DAOException {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    @Override
    public void rollback() throws DAOException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException();
        }
    }

    @Override
    public void end() throws DAOException {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.setAutoCommit(true);
                }
                connection.close();
            } catch (SQLException e) {
                logger.error(e);
                throw new DAOException();
            }
            connection = null;
        }
    }
}
