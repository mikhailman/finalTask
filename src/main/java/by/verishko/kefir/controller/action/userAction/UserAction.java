package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.action.Command;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class used to define the list of user action commands.
 */
public abstract class UserAction extends Command {
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
    public abstract void exec(HttpServletRequest request,
                              HttpServletResponse response) throws ServletException, IOException, ServiceException;
}
