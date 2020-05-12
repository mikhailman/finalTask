package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.controller.constantspath.ConstantsPath;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProfileCommand extends UserAction {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws DAOException, ServletException, IOException {
        UserService service = factory.createService(TypeDao.USER);
        User user = (User) request.getSession().getAttribute("authorizedUser");
        user = service.getUser(user.getIdUser());
        request.setAttribute("user", user);
        request.getRequestDispatcher(ConstantsPath.EDIT_PROFILE_PAGE).forward(request, response);
    }
}
