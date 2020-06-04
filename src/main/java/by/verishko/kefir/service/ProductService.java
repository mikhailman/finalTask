package by.verishko.kefir.service;

import by.verishko.kefir.entity.Product;
import by.verishko.kefir.service.exception.ServiceException;

import java.util.List;

public interface ProductService extends Service {

    /**
     * get list all products.
     *
     * @return list with products that found
     * @throws ServiceException no validate parameter.
     */
    List<Product> read() throws ServiceException;

    /**
     * create product.
     *
     * @param product object product.
     * @param idUser  user id.
     * @throws ServiceException no validate parameter product.
     */
    void createProduct(final Product product, final Integer idUser) throws ServiceException;

    /**
     * get product by user id.
     *
     * @param idUser user id.
     * @return product that found
     * @throws ServiceException sql exception.
     */
    Product getProduct(final Integer idProduct, final Integer idUser) throws ServiceException;

    /**
     * Update product
     *
     * @param product new parameter's product.
     * @throws ServiceException sql exception.
     */
    void updateProduct(final Product product) throws ServiceException;

    /**
     * Delete product.
     *
     * @param idProduct id product.
     * @throws ServiceException sql exception or number format id product.
     */
    void deleteProduct(final String idProduct) throws ServiceException;

}
