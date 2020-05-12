package by.verishko.kefir.service.Impl;

import by.verishko.kefir.dao.CategoryDAO;
import by.verishko.kefir.dao.ProductDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Product;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class ProductServiceImpl extends ServiceImpl implements ProductService {

    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * create product.
     *
     * @param product object disk.
     * @param idUser  user id.
     * @throws DAOException no validate parameter disk.
     */
    @Override
    public void createProduct(Product product, Integer idUser) throws DAOException {
        ProductDAO dao = transaction.createDao(TypeDao.PRODUCT);
        CategoryDAO category = transaction.createDao(TypeDao.CATEGORY);
        //todo add validator!!
        try {
            product.setUser_id(idUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get product by user id.
     *
     * @param idUser user id.
     * @return product that found
     * @throws DAOException sql exception.
     */
    @Override
    public Product getProduct(Integer idUser) throws DAOException {
        return null;
    }

    /**
     * Update product
     *
     * @param product new parameter's disk.
     * @throws DAOException sql exception.
     */
    @Override
    public void updateProduct(Product product) throws DAOException {

    }

    /**
     * Delete product.
     *
     * @param idProduct id product.
     * @throws DAOException sql exception or number format id disk.
     */
    @Override
    public void deleteProduct(String idProduct) throws DAOException {
        ProductDAO dao = transaction.createDao(TypeDao.PRODUCT);
        try {
            Integer productId = Integer.parseInt(idProduct);
            dao.delete(productId);
            transaction.commit();
            logger.debug("Product successfully deleted");
        } catch (DAOException e) {
            transaction.rollback();
            logger.error(e);
            throw new DAOException(e);

        }
    }
}
