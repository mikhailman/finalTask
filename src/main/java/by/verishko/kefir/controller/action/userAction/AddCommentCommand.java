package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.CommentService;
import by.verishko.kefir.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddCommentCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException, ServiceException {
        CommentService service = factory.createService(TypeDao.COMMENT);
        User user = (User) request.getSession().getAttribute("authorizedUser");
        String idProduct = request.getParameter("idProduct");
        String commentText = request.getParameter("comment");
        service.addComment(idProduct, commentText, user.getIdUser());
        String message = String.format("User %d added comment", user.getIdUser());
        logger.info(message);
        response.sendRedirect(request.getContextPath() + ConstantsPath.SHOW_PRODUCT_WITH_PARAMETER + idProduct + "#com");
    }
}
