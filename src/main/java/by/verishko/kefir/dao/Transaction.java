package by.verishko.kefir.dao;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.enumEntity.TypeDao;

public interface Transaction {

    <Type extends DAO<?>> Type createDao(final TypeDao key) throws DAOException;

    void commit() throws DAOException;

    void rollback() throws DAOException;

    void end() throws DAOException;

}