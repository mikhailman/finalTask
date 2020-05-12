package by.verishko.kefir.dao;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Comment;

import java.util.List;

public interface CommentDAO extends DAO<Comment> {

    List<Comment> readByIdProduct(final Integer idProduct) throws DAOException;

    Integer createComment(final Comment comment) throws DAOException;

}
