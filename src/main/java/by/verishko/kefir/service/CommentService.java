package by.verishko.kefir.service;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Comment;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.service.exception.ServiceException;

import java.util.Map;

public interface CommentService extends Service {
    /**
     * get comment about product by id product.
     *
     * @param idProduct integer id product.
     * @return map with user info and his comment.
     * @throws ServiceException sql exception.
     */
    Map<Comment, User> getComment(final Integer idProduct) throws ServiceException;

    /**
     * Add comment to product.
     *
     * @param idProduct id product.
     * @param comment   comment text.
     * @param idUser    id user.
     * @throws ServiceException sql exception.
     */
    void addComment(final String idProduct, final String comment, final Integer idUser) throws ServiceException;

    /**
     * delete comment by id user .
     *
     * @param idComment id comment.
     * @throws ServiceException sql exception.
     */
    void deleteComment(final String idComment) throws ServiceException;
}
