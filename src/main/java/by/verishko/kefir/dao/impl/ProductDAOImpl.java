package by.verishko.kefir.dao.impl;

import by.verishko.kefir.dao.ProductDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAOImpl extends BaseDao implements ProductDAO {

    private static final String CREATE_PRODUCT = "INSERT INTO kefir.products (name, description, price, users_id, category_id) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT id, name, description, price, date_creation, users_id, category_id FROM products ORDER BY date_creation DESC";
    private static final String GET_BY_ID_USER = "SELECT id, name, description, price, date_creation, category_id FROM products WHERE users_id = ? ORDER BY date_creation DESC";
    private static final String GET_BY_ID_PRODUCT = "SELECT id, name, description, price, date_creation, category_id FROM `products` WHERE id = ? ORDER BY date_creation DESC";
    private static final String UPDATE_PRODUCT = "UPDATE kefir.products SET name = ?, description = ?, price = ?, category_id = ? WHERE id = ?";
    private static final String DELETE_PRODUCT_BY_ID = "DELETE FROM products WHERE id = ?";

    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public Integer createProduct(final Product product) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_PRODUCT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getUser_id());
            statement.setInt(5, product.getCategory_id());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("There is no autoincremented index after trying to add record into table `comments`");
                    throw new DAOException();
                }
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<Product> read() throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_ALL);
            resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            Product product;
            while (resultSet.next()) {
                product = new Product();
                product.setIdProduct(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setDate_creation(resultSet.getTimestamp("date_creation"));
                product.setUser_id(resultSet.getInt("users_id"));
                product.setCategory_id(resultSet.getInt("category_id"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public List<Product> readByIdUser(final Integer idUser) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_BY_ID_USER);
            statement.setInt(1, idUser);
            resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            Product product;
            while (resultSet.next()) {
                product = new Product();
                product.setIdProduct(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setDate_creation(resultSet.getTimestamp("date_creation"));
                product.setCategory_id(resultSet.getInt("category_id"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void updateProduct(final Product product) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(UPDATE_PRODUCT);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getCategory_id());
            statement.setInt(5, product.getUser_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public Optional<Product> readByIdProduct(final Integer idProduct) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_BY_ID_PRODUCT);
            statement.setInt(1, idProduct);
            resultSet = statement.executeQuery();
            List<Product> products = new ArrayList<>();
            Product product;
            while (resultSet.next()) {
                product = new Product();
                product.setIdProduct(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setDescription(resultSet.getString("description"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setDate_creation(resultSet.getTimestamp("date_creation"));
                product.setCategory_id(resultSet.getInt("category_id"));
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    @Override
    public void delete(final Integer id) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }
}
