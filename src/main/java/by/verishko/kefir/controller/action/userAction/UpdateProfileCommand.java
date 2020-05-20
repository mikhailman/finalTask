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

public class UpdateProfileCommand extends UserAction {
    /**
     * Logger of class.
     */
    private static Logger logger = LogManager.getLogger();

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
        UserService service = factory.createService(TypeDao.USER);
        String oldPassword = request.getParameter("oldPassword");
        String repeatPassword = request.getParameter("password2");
        try {
            User newUser = getUser(request);
            logger.debug("newUser " + newUser);
            User oldUser = (User) request.getSession().getAttribute("authorizedUser");
            service.updateUser(newUser, oldUser.getIdUser(), oldPassword, repeatPassword);
            logger.debug("oldUser " + oldUser);
            String message = String.format("user %d  update profile", oldUser.getIdUser());
            logger.info(message);
            response.sendRedirect(request.getContextPath() + ConstantsPath.PROFILE);
//            request.getRequestDispatcher(ConstantsPath.PROFILE).forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServiceException(e);
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
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
//        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        User user = new User();
        user.setLogin(login);
        user.setName(name);
        user.setSurname(surname);
//        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        return user;
    }
}
