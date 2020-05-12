package by.verishko.kefir.controller.action.adminAction;

import by.verishko.kefir.controller.action.Command;
import by.verishko.kefir.dao.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AdminAction extends Command {

    public abstract void execute(final HttpServletRequest request,
                                 final HttpServletResponse response) throws DAOException, ServletException, IOException;
}
