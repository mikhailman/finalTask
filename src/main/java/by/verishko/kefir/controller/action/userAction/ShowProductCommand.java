package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Comment;
import by.verishko.kefir.entity.Product;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.CommentService;
import by.verishko.kefir.service.ProductService;
import by.verishko.kefir.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class ShowProductCommand extends UserAction {
    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException, ServletException, IOException {
        ProductService service = factory.createService(TypeDao.PRODUCT);
        CommentService commentService = factory.createService(TypeDao.COMMENT);
        User user = (User) request.getSession().getAttribute("authorizedUser");
        String idProduct = request.getParameter("product");
        Product product = service.getProduct(Integer.valueOf(idProduct), user.getIdUser());
        Map<Comment, User> mapComment = commentService.getComment(product.getIdProduct());
        request.setAttribute("mapComment", mapComment);
        request.setAttribute("product", product);
        request.getRequestDispatcher(ConstantsPath.SHOW_PRODUCT).forward(request, response);
    }
}
