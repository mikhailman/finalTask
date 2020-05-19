package by.verishko.kefir.service.Impl;

import by.verishko.kefir.dao.UserDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.Role;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.UserService;
import by.verishko.kefir.service.exception.ServiceException;
import by.verishko.kefir.service.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl extends ServiceImpl implements UserService {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * The validator provides the different types of checks for a given
     * parameters.
     */
    private Validator validator;

    @Override
    public User registerUser(User user, String repeatPassword) throws ServiceException {
        UserDAO dao = null;
        try {
            dao = transaction.createDao(TypeDao.USER);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        try {
//            boolean flag = dao.readByEmailAndNickname(user);
//            if (!flag) {
//                throw new ServiceException("errorLogin");
//            }
//            String salt = PasswordUtils.getSalt();
//            String hashPassword = PasswordUtils.generateHashPassword(user.getPassword(), salt);
//            user.setPassword(hashPassword);
            user.setRole(user.getRole());
            logger.debug("UserServiceImpl ROLE " + user.getRole());

//            if (!validator.validatePassword(repeatPassword)) {
//                throw new ServiceException("Password is not valid");
//            }

            // TODO: 02.04.2020 добавить валидатор!

// TODO: 07.05.2020 хэширование пароля!
            Integer id;
            id = dao.createUser(user);
            User resultUser = new User();
            resultUser.setIdUser(id);
            resultUser.setRole(user.getRole());
            logger.debug("UserServiceImpl " + resultUser.getRole());
            transaction.commit();
            logger.debug("user successfully registered");
            return resultUser;
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            logger.error(e + "user registration fail");
            throw new ServiceException(e);
        }
    }


    public User findUserByEmail(final String email, final String password) throws ServiceException {
        UserDAO dao = null;
        try {
            dao = transaction.createDao(TypeDao.USER);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
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
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public User getUser(final Integer id) throws ServiceException {
        UserDAO dao = null;
        try {
            dao = transaction.createDao(TypeDao.USER);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        try {
            Optional<User> user = dao.read(id);
            transaction.commit();
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new DAOException();
            }
        } catch (DAOException e) {
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateUser(User newUser, Integer idUser, String oldPassword,
                           String repeatPassword) throws ServiceException {
        UserDAO dao = null;
        try {
            dao = transaction.createDao(TypeDao.USER);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

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
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            logger.error(e + "user don't update");
            throw new ServiceException(e);
        }
    }

    /**
     * Show all users saved in database.
     *
     * @return all users.
     */
    @Override
    public List<User> findAll() throws ServiceException {
        List<User> userList = null;
        UserDAO dao = null;
        try {
            dao = transaction.createDao(TypeDao.USER);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        try {
            userList = dao.findAll();
            transaction.commit();
        } catch (DAOException e) {
            logger.error(e);
            try {
                transaction.rollback();
            } catch (DAOException ex) {
                throw new ServiceException(ex);
            }
            throw new ServiceException(e);
        }
        return userList;
    }
}
