package by.verishko.kefir.controller.action.adminAction;

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
import java.util.List;

public class DisplayAllUsersCommand extends AdminAction {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    private static final String LIST_OF_USERS_ATTRIBUTE = "usersList";

    @Override
    public void exec(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException, ServiceException {
        UserService userService = factory.createService(TypeDao.USER);
        List<User> list = userService.findAll();
        logger.debug("list " + list);
        request.setAttribute(LIST_OF_USERS_ATTRIBUTE, list);
        logger.debug("usersList " + request.getSession().getAttribute(LIST_OF_USERS_ATTRIBUTE));
        request.getRequestDispatcher(ConstantsPath.LIST_OF_USERS_JSP).forward(request, response);

    }
}
