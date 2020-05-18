package by.verishko.kefir.controller.action;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.service.ServiceFactory;
import by.verishko.kefir.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Concrete Action Manager implementation.
 */
public class CommandManagerImpl implements CommandManager {

    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * Represents Service Factory.
     */
    private ServiceFactory factory;

    /**
     * Constructs Command Manager Implementation instance with provided Service
     * factory.
     *
     * @param factory the provided Service factory instance.
     */
    CommandManagerImpl(final ServiceFactory factory) {
        this.factory = factory;
    }

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
    @Override
    public void execute(final Command command, final HttpServletRequest request,
                        final HttpServletResponse response) throws ServletException, IOException, ServiceException {
        command.setFactory(factory);
        logger.debug("ServiceFactory " + factory.toString());
        command.exec(request, response);

    }

    /**
     * Closes action manager. Implicitly returns connection to the connection
     * pool.
     */
    @Override
    public void close() {
        factory.close();
    }
}
