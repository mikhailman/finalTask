package by.verishko.kefir.service.Impl;

import by.verishko.kefir.dao.UserDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.UserService;
import by.verishko.kefir.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends ServiceImpl implements UserService {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public User registerUser(User user, String repeatPassword) throws DAOException {
        UserDAO dao = transaction.createDao(TypeDao.USER);
        // TODO: 02.04.2020 добавить валидатор!
        try {
// TODO: 07.05.2020 хэширование пароля!
            Integer id;
            id = dao.createUser(user);
            User resultUser = new User();
            resultUser.setIdUser(id);
            resultUser.setRole(user.getRole());
            transaction.commit();
            logger.debug("user successfully registered");
            return resultUser;
        } catch (DAOException e) {
            transaction.rollback();
            logger.error(e + "user registration fail");
            throw new DAOException(e);
        }
    }

    public User findUserByEmail(final String email, final String password) throws DAOException, ServiceException {
        UserDAO dao = transaction.createDao(TypeDao.USER);
        logger.debug("Email and password From findUserByEmail (UserServiceImpl) " + email + " " + password);
        try {
            Optional<User> user = dao.getPassword(email);
            logger.debug("User from UserService " + user);
            if (user.isPresent()) {
//                user.get().setPassword("");
                transaction.commit();
                logger.debug("User from UserServiceImpl (findUserByEmail) " + user);
                return user.get();
            } else {
                throw new ServiceException("unknownUser");
            }
        } catch (DAOException e) {
            transaction.rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public User getUser(final Integer id) throws DAOException {
        UserDAO dao = transaction.createDao(TypeDao.USER);
        try {
            Optional<User> user = dao.read(id);
            transaction.commit();
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new DAOException();
            }
        } catch (DAOException e) {
            transaction.rollback();
            throw new DAOException(e);
        }
    }

    @Override
    public void updateUser(User newUser, Integer idUser, String oldPassword,
                           String repeatPassword) throws DAOException {
        UserDAO dao = transaction.createDao(TypeDao.USER);

        try {
            Optional<User> oldUser = dao.findAllUserInfo(idUser);
            if (oldPassword != null && !oldPassword.isEmpty()) {
                if (oldUser.isPresent()) {
                    newUser.setPassword(oldPassword);
                } else {
                    throw new DAOException("Old value's user not found");
                }
            } else if (oldUser.isPresent()) {
                newUser.setPassword(oldUser.get().getPassword());
            } else {
                throw new DAOException();
            }
            newUser.setIdUser(oldUser.get().getIdUser());
            dao.update(newUser);
            transaction.commit();
            logger.debug("user successfully updated");
        } catch (DAOException e) {
            transaction.rollback();
            logger.error(e + "user don't update");
            throw new DAOException(e);
        }
    }

    /**
     * Show all users saved in database.
     *
     * @return all users.
     */
    @Override
    public List<User> findAll() throws DAOException {
        List<User> userList = null;
        UserDAO dao = transaction.createDao(TypeDao.USER);
        try {
            userList = dao.findAll();
            transaction.commit();
        } catch (DAOException e) {
            logger.error(e);
            transaction.rollback();
            throw new DAOException(e);
        }
        return userList;
    }
}
