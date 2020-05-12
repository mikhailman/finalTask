package by.verishko.kefir.service;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Category;

import java.util.List;

public interface CategoryService extends Service {
    /**
     * Get category.
     *
     * @return list category.
     * @throws DAOException sql exception.
     */
    List<Category> getCategory() throws DAOException;
}
