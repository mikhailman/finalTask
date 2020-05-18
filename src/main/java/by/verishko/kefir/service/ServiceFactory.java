package by.verishko.kefir.service;

import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.exception.ServiceException;

public interface ServiceFactory {
    <Type extends Service> Type createService(final TypeDao key) throws ServiceException;

    void close();
}
