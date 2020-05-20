package by.verishko.kefir.dao;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public interface DAO<Type extends Entity> {
    /**
     * Logger of class.
     */
    Logger logger = LogManager.getLogger(DAO.class.getName());

    default Integer create(final Type entity) throws DAOException {
        throw new DAOException();
    }

    default Optional<Type> read(final Integer id) throws DAOException {
        throw new DAOException();
    }

    default void update(final Type entity) throws DAOException {
        throw new DAOException();
    }

    default void delete(final Integer id) throws DAOException {
        throw new DAOException();
    }

    default List<Type> findAll() throws DAOException {
        throw new DAOException();
    }

    default void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException | NullPointerException e) {
            logger.error(e);
        }
    }

    default void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException | NullPointerException e) {
            logger.error(e);
        }
    }
}
