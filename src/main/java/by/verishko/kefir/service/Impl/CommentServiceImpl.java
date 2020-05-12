package by.verishko.kefir.service.Impl;

import by.verishko.kefir.dao.CommentDAO;
import by.verishko.kefir.dao.UserDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Comment;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommentServiceImpl extends ServiceImpl implements CommentService {

    private static final String REGEX_SENTENCE = "^(?!\\s\\t\\n\\r*$)[A-zА-яЁё0-9,.!@#?:()_\\t\\n\\r ]*$";

    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * get comment about product by id product.
     *
     * @param idProduct integer id product.
     * @return map with user info and his comment.
     * @throws DAOException sql exception.
     */
    @Override
    public Map<Comment, User> getComment(Integer idProduct) throws DAOException {
        try {
            CommentDAO dao = transaction.createDao(TypeDao.COMMENT);
            UserDAO userDAO = transaction.createDao(TypeDao.USER);
            List<Comment> commentList = dao.readByIdProduct(idProduct);
            Optional<User> user;
            Map<Comment, User> mapCommentAndUser = new LinkedHashMap<>();
            for (Comment comment : commentList) {
                user = userDAO.read(comment.getUser_id());
                user.ifPresent(user1 -> mapCommentAndUser.put(comment, user1));
            }
            transaction.commit();
            logger.info("Say mom Comment successfully added");
            return mapCommentAndUser;
        } catch (DAOException e) {
            transaction.rollback();
            logger.error(e);
            throw new DAOException(e);
        }
    }

    /**
     * Add comment to announcement.
     *
     * @param idProduct   id product.
     * @param commentBody comment text.
     * @param idUser      id user.
     * @throws DAOException sql exception.
     */
    @Override
    public void addComment(String idProduct, String commentBody, Integer idUser) throws DAOException {
        CommentDAO dao = transaction.createDao(TypeDao.COMMENT);
        Comment comment = new Comment();
        try {
            Integer id = Integer.parseInt(idProduct);
            if (commentBody != null && !commentBody.isEmpty() && commentBody.matches(REGEX_SENTENCE)) {
                comment.setCommentText(commentBody);
                comment.setProduct_id(id);
                comment.setUser_id(idUser);
                dao.createComment(comment);
                transaction.commit();
            } else {
                throw new DAOException("Comment not correct");
            }
        } catch (NumberFormatException | DAOException e) {
            transaction.rollback();
            logger.error(e);
            throw new DAOException(e);
        }
    }

    /**
     * delete comment by id.
     *
     * @param idComment id comment.
     * @throws DAOException sql exception.
     */
    @Override
    public void deleteComment(String idComment) throws DAOException {
        CommentDAO dao = transaction.createDao(TypeDao.COMMENT);
        try {
            Integer id = Integer.parseInt(idComment);
            dao.delete(id);
            transaction.commit();
        } catch (NumberFormatException | DAOException e) {
            transaction.rollback();
            logger.error(e);
            throw new DAOException(e);
        }
    }
}
