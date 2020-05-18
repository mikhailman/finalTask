package by.verishko.kefir.controller.action;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.service.ServiceFactory;
import by.verishko.kefir.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Common class for Commands that exist in the application.
 */
public abstract class Command {

    /**
     * Represents Service factory.
     */
    public ServiceFactory factory;

    /**
     * Gets factory.
     *
     * @return value of factory.
     */
    public void setFactory(final ServiceFactory factory) {
        this.factory = factory;
    }

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
    public abstract void exec(final HttpServletRequest request,
                              final HttpServletResponse response) throws ServletException, IOException, ServiceException;

}
