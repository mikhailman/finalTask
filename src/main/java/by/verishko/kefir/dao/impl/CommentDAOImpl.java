package by.verishko.kefir.dao.impl;

import by.verishko.kefir.dao.CommentDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Category;
import by.verishko.kefir.entity.Comment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl extends BaseDao implements CommentDAO {

    private static final String GET_COMMENTS = "SELECT id, name, body, users_id FROM comments WHERE kefir.comments.products_id = ?";
    private static final String CREATE_COMMENT = "INSERT INTO `comments` (name, body, users_id, products_id) VALUES (?, ?, ?, ?)";
    private static final String DELETE_COMMENT = "DELETE FROM `comments` WHERE id = ?";

    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public Integer createComment(final Comment comment) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_COMMENT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, comment.getName());
            statement.setString(2, comment.getCommentText());
            statement.setInt(3, comment.getUser_id());
            statement.setInt(4, comment.getProduct_id());
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
    public void delete(final Integer id) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_COMMENT);
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

    @Override
    public List<Comment> readByIdProduct(final Integer idProduct) throws DAOException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_COMMENTS);
            statement.setInt(1, idProduct);
            resultSet = statement.executeQuery();
            List<Comment> comments = new ArrayList<>();
            Comment comment;
            while (resultSet.next()) {
                comment = new Comment();
                comment.setIdComment(resultSet.getInt("id"));
                comment.setName(resultSet.getString("name"));
                comment.setCommentText(resultSet.getString("body"));
                comment.setUser_id(resultSet.getInt("users_id"));
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }
}