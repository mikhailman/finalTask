package by.verishko.kefir.controller.action;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommandManagerImpl implements CommandManager {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private ServiceFactory factory;

    CommandManagerImpl(final ServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    public void execute(final Command command, final HttpServletRequest request,
                        final HttpServletResponse response) throws DAOException, ServletException, IOException {
        command.setFactory(factory);
        logger.debug("ServiceFactory " + factory.toString());
        command.execute(request, response);
    }

    @Override
    public void close() {
        factory.close();
    }
}
