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

public class ProfileCommand extends UserAction {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response) throws ServiceException, ServletException, IOException {
        UserService service = factory.createService(TypeDao.USER);
        User user = (User) request.getSession().getAttribute("authorizedUser");
        user = service.getUser(user.getIdUser());
        request.setAttribute("user", user);
        logger.debug("authorizedUser from session " + request.getSession().getAttribute("authorizedUser"));
        logger.debug("User from session " + request.getSession().getAttribute("user"));
        request.getRequestDispatcher(ConstantsPath.MY_PROFILE).forward(request, response);
    }
}
