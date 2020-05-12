package by.verishko.kefir.service;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Product;

public interface ProductService extends Service {

    /**
     * create product.
     *
     * @param product object product.
     * @param idUser  user id.
     * @throws DAOException no validate parameter product.
     */
    void createProduct(final Product product, final Integer idUser) throws DAOException;

    /**
     * get product by user id.
     *
     * @param idUser user id.
     * @return product that found
     * @throws DAOException sql exception.
     */
    Product getProduct(final Integer idUser) throws DAOException;

    /**
     * Update product
     *
     * @param product new parameter's product.
     * @throws DAOException sql exception.
     */
    void updateProduct(final Product product) throws DAOException;

    /**
     * Delete product.
     *
     * @param idProduct id product.
     * @throws DAOException sql exception or number format id product.
     */
    void deleteProduct(final String idProduct) throws DAOException;

}
