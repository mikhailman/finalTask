package by.verishko.kefir.controller.action;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The interface used to manage commands.
 */
public interface CommandManager {

    /**
     * Sets the Service factory to provided Command. Encapsulates
     * Action command method invocation.
     *
     * @param command  the provided Command instance.
     * @param request  the provided request information for HTTP servlets.
     * @param response the provided response information for HTTP servlets.
     * @throws IOException      if an input or output error is
     *                          detected when the servlet handles
     *                          the request
     * @throws ServletException if the request for the GET or POST
     *                          could not be handled
     * @throws ServiceException if the service layer throws exception
     */
    void execute(final Command command, final HttpServletRequest request,
                 final HttpServletResponse response) throws IOException, ServletException, ServiceException;

    /**
     * Closes action manager. Implicitly returns connection to the connection
     * pool.
     */
    void close();
}
