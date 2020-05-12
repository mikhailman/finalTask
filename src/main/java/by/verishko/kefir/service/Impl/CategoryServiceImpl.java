package by.verishko.kefir.service.Impl;

import by.verishko.kefir.dao.CategoryDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Category;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.CategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryServiceImpl extends ServiceImpl implements CategoryService {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * Get category.
     *
     * @return list category.
     * @throws DAOException sql exception.
     */
    @Override
    public List<Category> getCategory() throws DAOException {
        try {
            CategoryDAO dao = transaction.createDao(TypeDao.CATEGORY);
            transaction.commit();
            logger.debug("dao.read() - " + dao.read());
            return dao.read();
        } catch (DAOException e) {
            transaction.rollback();
            throw new DAOException("Error CategoryDAO getCatalog", e);
        }
    }
}
