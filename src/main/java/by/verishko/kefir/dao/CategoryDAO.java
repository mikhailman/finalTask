package by.verishko.kefir.dao;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDAO extends DAO<Category> {

    List<Category> read() throws DAOException;

    Integer createCategory(final Category comment) throws DAOException;

//    Optional<Integer> read(final String name) throws DAOException;
}
