package by.verishko.kefir.service;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Category;
import by.verishko.kefir.service.exception.ServiceException;

import java.util.List;

public interface CategoryService extends Service {
    /**
     * Get category.
     *
     * @return list category.
     * @throws ServiceException sql exception.
     */
    List<Category> getCategory() throws ServiceException;
}
