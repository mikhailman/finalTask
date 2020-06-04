package by.verishko.kefir.service.Impl;

import by.verishko.kefir.dao.CategoryDAO;
import by.verishko.kefir.dao.ProductDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Category;
import by.verishko.kefir.entity.Product;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.ProductService;
import by.verishko.kefir.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl extends ServiceImpl implements ProductService {

    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * get list all products.
     *
     * @return list with products that found
     * @throws ServiceException no validate parameter.
     */
    @Override
    public List<Product> read() throws ServiceException {
        try {
            ProductDAO dao = transaction.createDao(TypeDao.PRODUCT);
            List<Product> listProduct = dao.read();
            transaction.commit();
            return listProduct;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * create product.
     *
     * @param product object product.
     * @param idUser  user id.
     * @throws ServiceException no validate parameter product.
     */
    @Override
    public void createProduct(final Product product, final Integer idUser) throws ServiceException {
        try {
            ProductDAO dao = transaction.createDao(TypeDao.PRODUCT);
            CategoryDAO category = transaction.createDao(TypeDao.CATEGORY);
            product.setUser_id(idUser);
            Optional<Category> idCategory = category.read(product.getCategory_id());
            if (idCategory.isPresent()) {
                Integer idProduct = dao.createProduct(product);
                product.setIdProduct(idProduct);
            } else {
                throw new ServiceException();
            }
            transaction.commit();
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(e);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * get product by user id.
     *
     * @param idUser user id.
     * @return product that found
     * @throws ServiceException sql exception.
     */
    @Override
    public Product getProduct(final Integer idProduct, final Integer idUser) throws ServiceException {
        ProductDAO dao = null;
        try {
            dao = transaction.createDao(TypeDao.PRODUCT);
            Optional<Product> product = dao.readByIdProduct(idProduct);
            if (product.isPresent()) {
                product.get().setIdProduct(idProduct);
            } else {
                throw new ServiceException("Product is null ");
            }
            transaction.commit();
            return product.get();
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * Update product
     *
     * @param product new parameter's product.
     * @throws ServiceException sql exception.
     */
    @Override
    public void updateProduct(Product product) throws ServiceException {
        // add validation
        try {
            ProductDAO dao = transaction.createDao(TypeDao.PRODUCT);
            dao.updateProduct(product);
            transaction.commit();
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            throw new ServiceException(e);
        }
    }

    /**
     * Delete product.
     *
     * @param idProduct id product.
     * @throws ServiceException sql exception or number format id product.
     */
    @Override
    public void deleteProduct(String idProduct) throws ServiceException {
        ProductDAO dao = null;
        try {
            dao = transaction.createDao(TypeDao.PRODUCT);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        try {
            Integer productId = Integer.parseInt(idProduct);
            dao.delete(productId);
            transaction.commit();
            logger.debug("Product successfully deleted");
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
