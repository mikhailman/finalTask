package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand extends UserAction {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        User user = (User) request.getSession().getAttribute("authorizedUser");
        logger.info("user \"%s\" is logged out", user.getIdUser());
        request.getSession(false).invalidate();
        response.sendRedirect(request.getContextPath() + ConstantsPath.HOME);
    }
}
