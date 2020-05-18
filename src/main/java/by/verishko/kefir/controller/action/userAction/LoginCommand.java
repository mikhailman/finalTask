package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.UserService;
import by.verishko.kefir.service.exception.ServiceException;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginCommand extends UserAction {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        HttpSession session = request.getSession(true);
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
                logger.debug("user from LoginCommand " + user);
//                session.setAttribute("authorizedUser", user);

                logger.debug("Finded user from LoginCommand " + user);
                request.getSession().setAttribute("authorizedUser", user);
                logger.debug("authorizedUser from session " + request.getSession().getAttribute("authorizedUser"));
//                logger.info("user \"%s\" is logged in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort());
//            message.put("url", request.getContextPath() + ConstantsPath.HOME);
//                request.getRequestDispatcher(ConstantsPath.HOME).forward(request, response);
                logger.debug("user detected " + message);
                logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" + request.getContextPath() + ConstantsPath.HOME);
                request.getRequestDispatcher(request.getContextPath() + "/main.html").forward(request, response);
//                request.getRequestDispatcher(ConstantsPath.MY_PROFILE).forward(request, response);
//                    if (user.getRole().equals(1)) {
//                        request.getRequestDispatcher(ConstantsPath.LOGIN_PAGE_ADMIN).forward(request, response);
//                    } else if (user.getRole().equals(2)) {
//                        request.getRequestDispatcher(ConstantsPath.LOGIN_PAGE_USER).forward(request, response);
            }
//            } else request.getRequestDispatcher(ConstantsPath.LOGIN_PAGE_HTML).forward(request, response);

//            String json = new Gson().toJson(message);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(json);
        } catch (ServiceException e) {
            logger.error(e);
            logger.info("user \"%s\" unsuccessfully tried to log in from %s (%s:%s)", login, request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort());
            request.getRequestDispatcher(ConstantsPath.ERROR_PAGE).forward(request, response);
//            message.put(e.getMessage(), e.getMessage());
//            String json = new Gson().toJson(message);
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//            response.getWriter().write(json);
        }
    }
}