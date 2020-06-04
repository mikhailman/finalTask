package by.verishko.kefir.service;

import by.verishko.kefir.entity.User;
import by.verishko.kefir.service.exception.ServiceException;

import java.util.List;

public interface UserService extends Service {

    /**
     * Registration user.
     *
     * @param user           user with parameter.
     * @param repeatPassword repeat password.
     * @return user with id and role.
     * @throws ServiceException not validate parameters.
     */
    User registerUser(final User user, String repeatPassword)
            throws ServiceException;

    /**
     * checking for existing user and password.
     *
     * @param email    email.
     * @param password password.
     * @return user with id and role if found
     * @throws ServiceException not found or not equals passwords.
     */
    User findUserByEmail(final String email, final String password)
            throws ServiceException;

    /**
     * Get user info by id for profile.
     *
     * @param id id user.
     * @return user .
     * @throws ServiceException sql exception.
     */
    User getUser(final Integer id) throws ServiceException;

    /**
     * updated user.
     *
     * @param newUser        new parameter for save.
     * @param idUser         id user.
     * @param oldPassword    old password.
     * @param repeatPassword repeat new password.
     * @throws ServiceException no validate parameters.
     */
    void updateUser(final User newUser, final Integer idUser,
                    final String oldPassword, final String repeatPassword) throws ServiceException;

    /**
     * updated password.
     *
     * @param id              id user.
     * @param oldPassword     old password.
     * @param newPassword     new password.
     * @param confirmPassword repeat new password.
     * @throws ServiceException no validate parameters.
     */
    void updatePassword(final Integer id, final String oldPassword,
                        final String newPassword, final String confirmPassword) throws ServiceException;

    /**
     * delete user.
     *
     * @param user user.
     * @throws ServiceException no validate parameters.
     */
    boolean deleteUser(final User user) throws ServiceException;

    /**
     * find all users.
     *
     * @return List of users
     * @throws ServiceException no validate parameters.
     */
    List<User> findAll() throws ServiceException;

}
