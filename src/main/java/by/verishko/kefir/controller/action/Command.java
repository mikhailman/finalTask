package by.verishko.kefir.controller.action;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.service.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    public ServiceFactory factory;

    public void setFactory(final ServiceFactory factory) {
        this.factory = factory;
    }

    public abstract void exec(final HttpServletRequest request,
                              final HttpServletResponse response) throws DAOException, ServletException, IOException;

}
