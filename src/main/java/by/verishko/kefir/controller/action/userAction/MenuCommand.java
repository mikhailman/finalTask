package by.verishko.kefir.controller.action.userAction;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Category;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.CategoryService;
import by.verishko.kefir.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class MenuCommand extends UserAction {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServiceException, ServletException, IOException {
        CategoryService service = factory.createService(TypeDao.CATEGORY);

        logger.debug("Factory create!");
        logger.debug("CategoryService " + service);

        List<Category> category = service.getCategory();

        logger.debug(category);

        request.setAttribute("category", category);

        logger.debug("Category create! All good!");
    }
}
