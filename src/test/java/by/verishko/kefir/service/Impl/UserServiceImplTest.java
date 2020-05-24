package by.verishko.kefir.service.Impl;

import by.verishko.kefir.dao.impl.TransactionFactoryImpl;
import by.verishko.kefir.entity.User;
import by.verishko.kefir.entity.enumEntity.Role;
import by.verishko.kefir.entity.enumEntity.TypeDao;
import by.verishko.kefir.service.ServiceFactory;
import by.verishko.kefir.service.UserService;
import by.verishko.kefir.service.exception.ServiceException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserServiceImplTest {

    private ServiceFactory factory;
    private UserService userService;

    @BeforeClass
    public void setUp() throws Exception {
        factory = new ServiceFactoryImpl(new TransactionFactoryImpl());
        userService = factory.createService(TypeDao.USER);
    }

    @AfterClass
    public void tearDown() throws Exception {
        factory.close();
    }

    @DataProvider(name = "dataForSaveException")
    private Object[][] getDataForSaveException() {
        return new Object[][]{
                {getUserForRegisterFail(1), "aaZZa"},
                {getUserForRegisterFail(2), "aaZZa4@"},
                {getUserForRegisterFail(3), null},
                {getUserForRegisterFail(4), "aa123"},
                {getUserForRegisterFail(5), "aaZZa44@"},
                {getUserForRegisterFail(6), "a"},
                {getUserForRegisterFail(7), "Aasd@12"},
        };
    }

    @Test(description = "Positive scenario of save user method", priority = 1)
    public void testPositiveSave() throws ServiceException {
        User user = new User();
        user.setName("Name");
        user.setSurname("Surname");
        user.setEmail("Banana@mail.com");
        user.setLogin("Login");
        user.setPassword("12324");
        user.setPhone("375291234567");
        User actualUser = userService.registerUser(user, "12324");
        User expectedUser = getExpectedUser();
        Assert.assertEquals(actualUser, expectedUser);
    }

    @Test(description = "Tests a save method to throw a ServiceException",
            expectedExceptions = Exception.class,
            dataProvider = "dataForSaveException", priority = 2)
    public void testSaveException(User user, String repeatPass)
            throws ServiceException {
        userService.registerUser(user, repeatPass);
    }

    @Test(description = "Positive scenario of update personal data method",
            priority = 3)
    public void testPositiveUpdatePersonalData() throws Exception {
        userService.updateUser(fillUserForUpdate(1), 2, "Banana@33", "Banana@22");
        User actual = userService.getUser(2);
        User expectedUser = fillUserForUpdate(0);
        Assert.assertEquals(actual, expectedUser);
    }

    @DataProvider(name = "dataForUpdatePersonalDataException")
    private Object[][] getDataForUpdatePersonalDataException() {
        return new Object[][]{
                {getUserForRegisterFail(1), 1, "", ""},
                {getUserForRegisterFail(2), 2, "aaZZa4@", "aaZZa4@"},
                {getUserForRegisterFail(3), 3, " ", ""},
                {getUserForRegisterFail(4), 4, "aa123", ""},
                {getUserForRegisterFail(5), 2, "aaZZa44@", "sda"},
                {getUserForRegisterFail(6), 4, "a", ""},
                {getUserForRegisterFail(7), 1, "Aasd@12", ""},
                {fillUserForUpdate(0), 3, "", ""}
        };
    }

    @Test(description = "Tests a update personal data method to throw a"
            + " ServiceException",
            expectedExceptions = Exception.class,
            dataProvider = "dataForUpdatePersonalDataException", priority = 4)
    public void testUpdatePersonalDataException(User user, Integer idUser,
                                                String oldPassword, String repeatPassword)
            throws ServiceException {
        userService.updateUser(user, idUser, oldPassword, repeatPassword);
    }

    @Test(description = "Positive scenario of get personal data method",
            priority = 5)
    public void testPositiveGetPersonalData() throws Exception {
        User actualUser = userService.getUser(2);
        Assert.assertEquals(actualUser, fillUserForUpdate(0));
    }

    @DataProvider(name = "dataForGetPersonalDataException")
    private Object[] getDataForGetPersonalDataException() {
        return new Object[]{0, 4, 5, 6, 7, 8, 9, 10, 123123, 1231, -123
        };
    }

    @Test(description = "Tests a get personal data method to throw a"
            + "ServiceException",
            expectedExceptions = ServiceException.class,
            dataProvider = "dataForGetPersonalDataException", priority = 6)
    public void testGetPersonalDataException(Integer idUser)
            throws ServiceException {
        userService.getUser(idUser);
    }

    @Test(description = "Positive scenario find by email and password",
            priority = 7)
    public void testPositiveFindByEmailAndPassword() throws Exception {
        User actualUser = userService.findUserByEmail("Banana@mail.com", "Banana@22");
        User expectedUser = getExpectedUser();
        expectedUser.setPassword("");
        Assert.assertEquals(actualUser, expectedUser);
    }

    @DataProvider(name = "dataForFindByEmailAndPasswordException")
    private Object[][] getDataForFindByEmailAndPasswordException() {
        return new Object[][]{
                {"Asdasd@mail.com", "asdqw"},
                {"Banana@mail.com", null},
                {null, "asda"},
                {"qwexzcz", ""},
        };
    }

    @Test(description = "Tests a get personal data method to throw a"
            + "ServiceException",
            expectedExceptions = Exception.class,
            dataProvider = "dataForFindByEmailAndPasswordException", priority = 8)
    public void testFindByEmailAndPasswordException(String email, String password)
            throws ServiceException {
        userService.findUserByEmail(email, password);
    }

    private User fillUserForUpdate(Integer number) {
        User user = new User();
        user.setName("Name");
        user.setSurname("Surname");
        user.setEmail("Banana@mail.com");
        user.setLogin("Login");
        user.setPhone("+375298283127");
        if (number == 1) {
            user.setPassword("Banana@22");
        }
        return user;
    }

    private User getExpectedUser() {
        User user = new User();
        user.setIdUser(18);
        user.setRole(Role.USER);
        user.setName("Name");
        user.setSurname("Surname");
        user.setEmail("Banana@mail.com");
        user.setLogin("Login");
        user.setPassword("12324");
        user.setPhone("375291234567");
        return user;
    }

    private User getUserForRegisterFail(Integer number) {
        User user = new User();
        switch (number) {
            case 1:
                user.setLogin("Login1");
                break;
            case 2:
                user.setName("Name");
                user.setSurname("Surname");
                break;
            case 3:
                user.setLogin("Login");
                user.setName("Name");
                user.setSurname("Surname");
                user.setEmail("Banana");
                break;
            case 4:
                user.setLogin("Login");
                user.setName("Name");
                user.setSurname("Surname");
                user.setEmail("Banana@mail.com");
                user.setPhone("asda");
                break;
            case 5:
                user.setLogin("Login");
                user.setName("Name");
                user.setSurname("Surname");
                user.setEmail("Banana@mail.com");
                user.setPhone("+37529 8283127");
                user.setPassword("asd");
                break;
            case 6:
                user.setLogin("Login");
                user.setName("Name");
                user.setSurname("Surname");
                user.setEmail("Banana@mail.com");
                user.setPhone("+37529 8283127");
                user.setPassword("asdA@12");
                break;
            case 7:
                user.setLogin("Login");
                user.setName("Name");
                user.setSurname("Surname");
                user.setEmail("Banana@mail.com");
                user.setPhone("+37529 8283127");
                user.setPassword("Aasd@12");
                break;
        }
        return user;
    }
}