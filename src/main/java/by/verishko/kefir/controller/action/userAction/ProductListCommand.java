package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.entity.Product;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.CategoryService;
import by.verishko.kefir.service.ProductService;
import by.verishko.kefir.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductListCommand extends UserAction {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * Handles the request and response and invoke appropriate method in the
     * Service Layer.
     *
     * @param request  the provided request information for HTTP servlets.
     * @param response the provided response information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     * @throws ServiceException if the service layer throws exception
     */
    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ProductService productService = factory.createService(TypeDao.PRODUCT);
//        CategoryService categoryService = factory.createService(TypeDao.CATEGORY);
//        User user = (User) request.getSession().getAttribute("authorizedUser");

        List<Product> products = productService.read();
        request.getSession().setAttribute("products", products);
        logger.debug(request.getSession().getAttribute("products"));
        request.getRequestDispatcher(ConstantsPath.LIST_OF_PRODUCTS_JSP).forward(request, response);

    }
}
