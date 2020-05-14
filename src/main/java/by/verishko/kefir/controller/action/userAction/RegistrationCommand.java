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
import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand extends UserAction {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws DAOException, IOException, ServletException {
        UserService userService = factory.createService(TypeDao.USER);
        Map<String, String> messages = new HashMap<>();
        try {
            User user = getUser(request);
            String repeatPassword = request.getParameter("password2");

            user = userService.registerUser(user, repeatPassword);
            request.getSession().setAttribute("authorizedUser", user);
            String message = String.format("user \"%d\" is registered in from %s (%s:%s)",
                    user.getIdUser(), request.getRemoteAddr(), request.getRemoteHost(), request.getRemotePort());
            logger.info(message);
            messages.put("url", request.getContextPath() + ConstantsPath.MY_PROFILE);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

        } catch (ServiceException e) {
            messages.put(e.getMessage(), e.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
        }
    }

    /**
     * Gets user data from request parameters.
     *
     * @param request the provided request
     * @return the user entity.
     */
    private User getUser(final HttpServletRequest request) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setName(name);
        user.setSurname(surname);
        return user;
    }
}
