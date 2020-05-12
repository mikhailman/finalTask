package by.verishko.kefir.controller.action;

import by.verishko.kefir.dao.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface CommandManager {

    void execute(final Command command, final HttpServletRequest request,
                 final HttpServletResponse response) throws IOException, ServletException, DAOException;

    void close();
}
