package by.verishko.kefir.dao.impl;

import by.verishko.kefir.dao.UserDAO;
import by.verishko.kefir.dao.exception.DAOException;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends BaseDao implements UserDAO {

    private static final String CREATE_USER = "INSERT INTO `users` (`login`, `password`, `email`, " +
            "`phone`, `name`, `surname`) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_USERS = "SELECT id, login, password, email, name, surname FROM `users` AS u WHERE status != 0;";

    private static final String SELECT_USER_BY_ID = "SELECT users.name FROM user WHERE id = ?";

    private static final String GET_USER_BY_ID = "SELECT `login`, `name`, `surname`, " +
            "`email`, `phone`, `date_registration` FROM `users` WHERE `id` = ?";

    private static final String DELETE_USER_BY_ID = "DELETE FROM `users` WHERE `id` = ?";

//    private static final String DELETE_USER_BY_ID = "UPDATE `users` SET `status` = ? FROM `users` WHERE `id` = ?";

    private static final String UPDATE_USER = "UPDATE users SET role = ?, login = ?, password = ?," +
            " email = ?, phone = ?, name = ?, surname = ?, status = ?, date_registration = ? WHERE kefir.users.id = ?;";

    private static final String SELECT_USER_BY_LOGIN_PWD = "SELECT id, login FROM users WHERE login = ? " +
            "and password = ?";

    private static final String GET_ALL_INFO_BY_ID = "SELECT id, role, login, password, email, phone, name, " +
            "surname, status, date_registration FROM users WHERE `id` = ?";

    private static final String GET_PASSWORD = "SELECT `id`,`password` FROM kefir.`users` WHERE `email` = ?";

    private static final String INSERT_INTO_USERS = "INSERT INTO users(id, login, password, email, phone, name, " +
            "surname, status, date_registration, role) VALUES (?,?,?,?,?,?,?,?,?,?)";

    private static final String SELECT_USER_BY_LOGIN = "SELECT id, login, name FROM users WHERE login = ?";

    private static final String SELECT_USER_BY_EMAIL = "SELECT id, login, name FROM users WHERE email = ?";

    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * Save new user to database.
     *
     * @param user - user which will saved to database.
     * @return - result of saving. return true value if successfully.
     * @throws DAOException - generating if some values conflicts
     *                      with existed in database.
     */
//    @Override
//    public User create(User user) throws DAOException {
//        PreparedStatement statement = null;
//        try {
//            statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
////            statementUpdateInfoUser(user, statement);
//            statement.setInt(1, user.getRole().getIdRole());
//            statement.setString(2, user.getLogin());
//            statement.setString(3, user.getPassword());
//            statement.setString(4, user.getEmail());
//            statement.setLong(5, user.getPhone());
//            statement.setString(6, user.getName());
//            statement.setString(7, user.getSurname());
//            statement.setBoolean(8, user.isActiveStatus());
//            statement.setString(9, user.getDate_registration().toString());
//            statement.executeUpdate();
//            try (ResultSet resultSet = statement.getGeneratedKeys()) {
//                if (resultSet.next()) {
//                    int userId = resultSet.getInt(1);
//                    user.setId(userId);
//                } else {
//                    logger.error("There is no autoincremented index after trying to add record into table `users`");
//                    throw new DAOException();
//                }
//            }
//        } catch (SQLException e) {
//            logger.error(e);
//            throw new DAOException(e);
//        } finally {
//            close(statement);
//            close(connection);
//        }
//        return user;
//    }
    @Override
    public Integer createUser(final User user) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
//            statement.setInt(1, user.getRole().getIdRole());
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getName());
            statement.setString(6, user.getSurname());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    logger.error("There is no autoincremented index after trying to add record into table `users`");
                    throw new DAOException();
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

//    private void statementUpdateInfoUser(User user, PreparedStatement statement) throws SQLException {
//        statement.setInt(1, user.getIdUser());
//        statement.setString(2, user.getLogin());
//        statement.setString(3, user.getPassword());
//        statement.setString(4, user.getEmail());
//        statement.setLong(5, user.getPhone());
//        statement.setString(6, user.getName());
//        statement.setString(7, user.getSurname());
//        statement.setBoolean(8, user.isActiveStatus());
//        statement.setString(9, user.getDate_registration().toString());
//        statement.setInt(10, user.getRole().getIdRole());
//        statement.executeUpdate();
//    }

    /**
     * Save new user to database.
     *
     * @return - user.
     * @throws DAOException - generating if some values conflicts
     *                      with existed in database.
     */
    @Override
    public Optional<User> read(Integer id) throws DAOException {
        User user = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(GET_USER_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setLogin(resultSet.getString("login"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setPhone(resultSet.getString("phone"));
                user.setDate_registration(LocalDate.parse(resultSet.getString("date_registration")));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * Update user values.
     *
     * @param user - user which needed to be update
     *             with new params.
     * @return - user with new params.
     */
    @Override
    public User update(User user) throws DAOException {
        PreparedStatement statement = null;
        try {

//            "UPDATE `users` SET `login` = ?, `password` = ?, `email` = ?, " +
//                    "`phone` = ?, `name` = ?, `surname` = ?, `status` = ?, `date_registration` = ?, " +
//                    "`role` = ? WHERE `users.id` = ?";

            statement = connection.prepareStatement(UPDATE_USER);
//            statementUpdateInfoUser(user, statement);
            statement.setInt(1, user.getRole().getIdRole());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPhone());
            statement.setString(6, user.getName());
            statement.setString(7, user.getSurname());
            statement.setBoolean(8, user.isActiveStatus());
            statement.setString(9, user.getDate_registration().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
        return user;
    }

    /**
     * Remove user from database.
     *
     * @param id - identity of removing user.
     */
    @Override
    public void delete(Integer id) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_USER_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            close(statement);
            close(connection);
        }
    }

    /**
     * Find all users from database.
     *
     * @return - list of users which were found.
     * @throws DAOException - generated if some problems
     *                      with connecting to database.
     */
    @Override
    public List<User> findAll() throws DAOException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS)) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setIdUser(Integer.parseInt(resultSet.getString("id")));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        }
        return users;
    }

    /**
     * Find user by login and password from database.
     *
     * @return - user.
     * @throws DAOException - generated if some problems
     *                      with connecting to database.
     */
    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = null;
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_USER_BY_LOGIN_PWD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return user;
    }

    @Override
    public Optional<User> getPassword(final String email) throws DAOException {
        try (PreparedStatement statement = connection.prepareStatement(GET_PASSWORD)) {

            statement.setString(1, email);
            logger.debug("Statement from UserDAOImpl " + statement);
            try (ResultSet resultSet = statement.executeQuery()) {

                User user = null;
                if (resultSet.next()) {
                    user = new User();
                    logger.debug("Resultset getPassword " + resultSet);
                    user.setIdUser(resultSet.getInt("id"));
                    user.setPassword(resultSet.getString("password"));
                }
                logger.debug("User from UserDAOImpl " + user);
                return Optional.ofNullable(user);
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        } catch (
                SQLException e) {
            throw new DAOException(e);
        }
    }

    /**
     * Find all user info from database.
     *
     * @return - user info.
     * @throws DAOException - generated if some problems
     *                      with connecting to database.
     */
    @Override
    public Optional<User> findAllUserInfo(Integer id) throws DAOException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(GET_ALL_INFO_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = null;
            try {
                resultSet = statement.executeQuery();
                User user = null;
                if (resultSet.next()) {
                    user = new User();
                    user.setIdUser(resultSet.getInt("id"));
                    user.setRole(Role.getByIdRole(resultSet.getInt("role")));
                    user.setLogin(resultSet.getString("login"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPhone(resultSet.getString("phone"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setActiveStatus(resultSet.getInt("status"));
                    user.setDate_registration(LocalDate.parse(resultSet.getString("date_registration")));
                }
                return Optional.ofNullable(user);
            } catch (SQLException e) {
                logger.error(e);
                throw new DAOException(e);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }
}
