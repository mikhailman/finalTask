package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.CommentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCommentCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws DAOException, ServletException, IOException {
        CommentService service = factory.createService(TypeDao.COMMENT);
        User user = (User) request.getSession().getAttribute("authorizedUser");
        String idProduct = request.getParameter("product");
        String idComment = request.getParameter("com");
        service.deleteComment(idComment);
        String message = String.format("User %d deleted comment %s on announcement %s", user.getIdUser(), idComment, idProduct);
        logger.info(message);
        response.sendRedirect(request.getContextPath() + ConstantsPath.SHOW_PRODUCT_WITH_PARAMETER + idProduct + "#com");
    }
}
