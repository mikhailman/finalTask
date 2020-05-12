package by.verishko.kefir.service.Impl;

import by.verishko.kefir.dao.Transaction;
import by.verishko.kefir.dao.TransactionFactory;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.Service;
import by.verishko.kefir.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceFactoryImpl implements ServiceFactory {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private TransactionFactory transactionFactory;

    public ServiceFactoryImpl(final TransactionFactory transactionFactory) {
        this.transactionFactory = transactionFactory;
    }

    public ServiceImpl getService(final TypeDao key) throws DAOException {
        ServiceImpl service;
        switch (key) {
            case USER:
                service = new UserServiceImpl();
                break;
//            case PRODUCT:
//                service = new ProductServiceImpl();
//                break;
            case CATEGORY:
                service = new CategoryServiceImpl();
                break;
            case COMMENT:
                service = new CommentServiceImpl();
                break;
//            case LOCATION:
//                service = new LocationServiceImpl();
//                break;
//            case IMAGE:
//                service = new ImageServiceImpl();
//                break;
            default:
                String message = "Incorrect type of Service " + key;
                logger.error(message);
                throw new DAOException(message);
        }
        return service;
    }

    @Override
    public <Type extends Service> Type createService(TypeDao key) throws DAOException {
        ServiceImpl service = getService(key);
        Transaction transaction = transactionFactory.createTransaction();
        service.setTransaction(transaction);
        return (Type) service;
    }

    @Override
    public void close() {
        transactionFactory.close();
    }
}
