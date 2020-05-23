package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.UserService;
import by.verishko.kefir.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProfileCommand extends UserAction {
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
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException, ServletException, IOException {
        logger.debug("start edit profile page");
        UserService service = factory.createService(TypeDao.USER);
        User user = (User) request.getSession().getAttribute("authorizedUser");
        user = service.getUser(user.getIdUser());
        request.setAttribute("user", user);
        request.getRequestDispatcher(ConstantsPath.EDIT_PROFILE_PAGE).forward(request, response);
    }
}
