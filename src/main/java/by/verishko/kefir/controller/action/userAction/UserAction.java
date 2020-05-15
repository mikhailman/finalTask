package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.action.Command;
import by.verishko.kefir.dao.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class UserAction extends Command {

    public abstract void exec(HttpServletRequest request,
                              HttpServletResponse response) throws DAOException, ServletException, IOException;
}
