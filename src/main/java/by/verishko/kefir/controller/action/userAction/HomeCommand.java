package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.dao.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand extends UserAction {
    @Override
    public void exec(HttpServletRequest request,
                     HttpServletResponse response) throws DAOException, ServletException, IOException {
        request.getRequestDispatcher(ConstantsPath.INDEX_PAGE).forward(request, response);
    }
}
