package by.verishko.kefir.controller.action.adminAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisplayAllUsers extends AdminAction {

    private static final String LIST_OF_USERS_ATTRIBUTE = "usersList";

    @Override
    public void execute(final HttpServletRequest request,
                        final HttpServletResponse response) throws DAOException, ServletException, IOException {
        UserService userService = factory.createService(TypeDao.USER);
        List<User> list = userService.findAll();

        request.setAttribute(LIST_OF_USERS_ATTRIBUTE, list);
        request.getRequestDispatcher(ConstantsPath.LIST_OF_USERS_JSP).forward(request, response);

    }
}