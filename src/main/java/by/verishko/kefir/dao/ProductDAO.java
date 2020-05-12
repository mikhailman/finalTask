package by.verishko.kefir.dao;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDAO extends DAO<Product> {

    Integer createProduct(final Product product) throws DAOException;

    List<Product> read() throws DAOException;

    List<Product> readByIdUser(final Integer idUser) throws DAOException;

    default List<Product> readProductByParam(final Double price, final String name,
                                             final Integer category) throws DAOException {
        throw new DAOException();
    }

    void updateProduct(final Product product) throws DAOException;

    Optional<Product> readByIdProduct(final Integer idProduct) throws DAOException;

}
