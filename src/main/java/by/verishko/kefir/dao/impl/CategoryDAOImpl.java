package by.verishko.kefir.dao.impl;

import by.verishko.kefir.dao.CategoryDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends BaseDao implements CategoryDAO {

    private static final String READ_ALL = "SELECT `id`, `name` from `category`";
    private static final String CREATE_CATEGORY = "INSERT INTO `category` (`name`) VALUES (?)";

    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public Integer createCategory(Category category) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_CATEGORY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("There is no autoincremented index after trying to add record into table `category`");
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
    public List<Category> read() throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_ALL);
            List<Category> categories = new ArrayList<>();
            Category category;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                category = new Category();
                category.setIdCatalog(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }
            logger.debug("List существующих категорий: " + categories);
            return categories;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }
}
