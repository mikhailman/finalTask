package by.verishko.kefir.controller.action.adminAction;

import by.verishko.kefir.controller.action.Command;
import by.verishko.kefir.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AdminAction extends Command {

    public abstract void exec(final HttpServletRequest request,
                              final HttpServletResponse response) throws ServletException, IOException, ServiceException;
}
