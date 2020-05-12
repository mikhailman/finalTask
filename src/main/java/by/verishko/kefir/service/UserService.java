package by.verishko.kefir.service;

import by.verishko.kefir.dao.DAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.service.exception.ServiceException;

import java.util.List;

public interface UserService extends Service {

    User registerUser(final User user, String repeatPassword)
            throws DAOException, ServiceException;

    User findUserByEmail(final String email, final String password)
            throws DAOException, ServiceException;

    User getUser(final Integer id) throws DAOException;

    void updateUser(final User newUser, final Integer idUser,
                    final String oldPassword, final String repeatPassword) throws DAOException, ServiceException;

    List<User> findAll() throws DAOException;

}
