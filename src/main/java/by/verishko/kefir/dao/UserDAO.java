package by.verishko.kefir.dao;

import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.service.exception.ServiceException;

import java.util.Optional;

public interface UserDAO extends DAO<User> {

    Integer createUser(final User user) throws DAOException;

    User findUserByLoginAndPassword(String login, String password) throws DAOException;

    Boolean readByEmailAndNickname(final User user) throws DAOException;

    Optional<User> getPassword(final String email) throws DAOException;

    Optional<User> findAllUserInfo(final Integer id) throws DAOException;

    void updatePassword(User user) throws DAOException;

//    Optional<User> findById(final Integer id) throws DAOException;


    //  Мы создаем в UserDAO те операции которые можем выполнять мы над юзером!

}
