package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
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
import java.util.HashMap;
import java.util.Map;

public class LoginCommand extends UserAction {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("email");
        String password = request.getParameter("password");
        logger.debug("Login and pass from LoginCommand " + login + " " + password);
        logger.debug("login and password received");
        Map<String, String> message = new HashMap<>();
        User user = null;
        logger.debug("User is created " + user);
        try {
            if (login != null && password != null) {
                UserService service = factory.createService(TypeDao.USER);
                logger.debug("UserService from LoginCommand " + service);
                user = service.findUserByEmail(login, password);

                logger.debug("Finded user from LoginCommand " + user);
                request.getSession().setAttribute("authorizedUser", user);
                logger.debug("authorizedUser from session " + request.getSession().getAttribute("authorizedUser"));
                logger.debug("user detected " + message);
                logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + request.getContextPath() + ConstantsPath.HOME);
                response.sendRedirect(request.getContextPath() + "/main.html");
            }
        } catch (ServiceException e) {
            logger.error(e);
            logger.info("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort());
            request.getRequestDispatcher(ConstantsPath.ERROR_PAGE).forward(request, response);
        }
    }
}