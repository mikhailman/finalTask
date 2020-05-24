package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.UserService;
import by.verishko.kefir.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangePasswordCommand extends UserAction {
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
     * @throws ServiceException if the service layer throws exception
     */
    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServiceException {
        logger.debug("start Change Password command page");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");
        User authorizedUser = (User) request.getSession().getAttribute("authorizedUser");
        logger.debug("authorizedUser from ChangePasswordCommand " + authorizedUser);
        try {
            UserService service = factory.createService(TypeDao.USER);
            service.updatePassword(authorizedUser.getIdUser(), oldPassword, newPassword, confirmNewPassword);
            String message = String.format("user %d  update password", authorizedUser.getIdUser());
            logger.info(message);
            response.sendRedirect(request.getContextPath() + ConstantsPath.PROFILE);
            logger.debug("end Change Password command page");
        } catch (ServiceException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
