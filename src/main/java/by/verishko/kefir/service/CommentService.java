package by.verishko.kefir.service;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Comment;
import by.verishko.kefir.entity.User;

import java.util.Map;

public interface CommentService extends Service {
    /**
     * get comment about product by id product.
     *
     * @param idProduct integer id product.
     * @return map with user info and his comment.
     * @throws DAOException sql exception.
     */
    Map<Comment, User> getComment(final Integer idProduct) throws DAOException;

    /**
     * Add comment to announcement.
     *
     * @param idProduct id product.
     * @param comment   comment text.
     * @param idUser    id user.
     * @throws DAOException sql exception.
     */
    void addComment(final String idProduct, final String comment, final Integer idUser) throws DAOException;

    /**
     * delete comment by id user .
     *
     * @param idComment id comment.
     * @throws DAOException sql exception.
     */
    void deleteComment(final String idComment) throws DAOException;
}
