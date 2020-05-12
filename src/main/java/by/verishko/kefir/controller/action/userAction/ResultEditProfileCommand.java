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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultEditProfileCommand extends UserAction {
    private static Logger logger = LogManager.getLogger();

    @Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) throws DAOException, ServletException, IOException {
        UserService service = factory.createService(TypeDao.USER);
        Map<String, String> messages = new HashMap<>();
        String oldPassword = request.getParameter("oldPassword");
        String repeatPassword = request.getParameter("password2");
        try {
            User newUser = getUser(request);
            User oldUser = (User) request.getSession().getAttribute("authorizedUser");
            service.updateUser(newUser, oldUser.getIdUser(), oldPassword, repeatPassword);
            String message = String.format("user %d  update profile", oldUser.getIdUser());
            logger.info(message);
            messages.put("url", request.getContextPath() + ConstantsPath.MY_PROFILE);
            String json = new Gson().toJson(message);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        } catch (ServiceException e) {
            messages.put(e.getMessage(), e.getMessage());
            String json = new Gson().toJson(messages);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
        }
    }

    /**
     * Gets user data from request parameters.
     *
     * @param request the provided request
     * @return the user entity.
     */
    private User getUser(final HttpServletRequest request) throws IOException, ServletException {
        String login = request.getParameter("login");
        String firstName = request.getParameter("name");
        String lastName = request.getParameter("surname");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setName(firstName);
        user.setSurname(lastName);
        user.setPhone(Long.valueOf(phone));
        user.setLogin(login);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }
}
