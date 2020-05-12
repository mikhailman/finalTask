package by.verishko.kefir.service;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.enumEntity.TypeDao;

public interface ServiceFactory {
    <Type extends Service> Type createService(final TypeDao key) throws DAOException;

    void close();
}
