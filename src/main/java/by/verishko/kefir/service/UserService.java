package by.verishko.kefir.service;

import by.verishko.kefir.entity.User;
import by.verishko.kefir.service.exception.ServiceException;

import java.util.List;

public interface UserService extends Service {

    User registerUser(final User user, String repeatPassword)
            throws ServiceException;

    User findUserByEmail(final String email, final String password)
            throws ServiceException;

    User getUser(final Integer id) throws ServiceException;

    void updateUser(final User newUser, final Integer idUser,
                    final String oldPassword, final String repeatPassword) throws ServiceException;

    List<User> findAll() throws ServiceException;

}
