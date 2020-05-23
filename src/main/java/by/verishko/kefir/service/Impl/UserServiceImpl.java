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

    @Override
    public User registerUser(final User user, final String repeatPassword) throws ServiceException {
        logger.debug("repeatPassword from UserServiceImpl " + repeatPassword);
        Validator validator = new Validator();
        if (!validator.validatePassword(repeatPassword)) {
            throw new ServiceException("Password is not valid");
        }
        UserDAO dao = null;
        try {
            dao = transaction.createDao(TypeDao.USER);
            boolean flag = dao.readByEmailAndNickname(user);
            if (!flag) {
                throw new ServiceException("errorLogin ");
            }
//            String salt = PasswordUtils.getSalt();
//            String hashPassword = PasswordUtils.generateHashPassword(user.getPassword(), salt);
//            user.setPassword(hashPassword);
            user.setRole(Role.USER);
            logger.debug("UserServiceImpl ROLE = " + user.getRole());
            logger.debug("repeatPassword " + repeatPassword);
            Integer id;
            id = dao.createUser(user);
            logger.debug("user from UserService " + user);
            User resultUser = new User();
            resultUser.setIdUser(id);
            resultUser.setRole(user.getRole());
            resultUser.setName(user.getName());
            logger.debug("userName from UserService " + user.getName());
            logger.debug("resultUserName from UserService " + resultUser.getName());
            resultUser.setSurname(user.getSurname());
            resultUser.setPhone(user.getPhone());
            logger.debug("User from UserServiceImpl " + resultUser);
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
    public void updateUser(final User newUser, final Integer idUser, String oldPassword,
                           String repeatPassword) throws ServiceException {
        UserDAO dao = null;
        Validator validator = new Validator();
        validator.validatePassword(oldPassword);
        validator.validatePassword(repeatPassword);
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

    @Override
    public void updatePassword(Integer id, String oldPassword, String newPassword, String confirmPassword) throws ServiceException {
        UserDAO dao = null;
        Validator validator = new Validator();
        if (!validator.validatePassword(newPassword)) {
            throw new ServiceException("invalid password format");
        }
        try {
            User newUser = new User();
            logger.debug("newUser " + newUser);
            dao = transaction.createDao(TypeDao.USER);
            Optional<User> oldUser = dao.findAllUserInfo(id);
            logger.debug("oldUser from UserServiceImpl " + oldUser);
            if (oldPassword != null && !oldPassword.isEmpty()) {
                if (oldUser.isPresent()) {
                    newUser.setPassword(newPassword);
                } else {
                    throw new DAOException("Old value's user not found");
                }
            } else if (oldUser.isPresent()) {
                newUser.setPassword(oldUser.get().getPassword());
            } else {
                throw new DAOException();
            }
            newUser.setIdUser(oldUser.get().getIdUser());
            dao.updatePassword(newUser);
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

    @Override
    public boolean deleteUser(final User user) throws ServiceException {
        UserDAO dao = null;
        if (findUserByEmail(user.getEmail(), user.getPassword()) != null) {
            try {
                dao = transaction.createDao(TypeDao.USER);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
            try {
                dao.delete(user.getIdUser());
                transaction.commit();
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
            return true;
        }
        return false;
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
