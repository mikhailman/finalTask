package by.verishko.kefir.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    private static final String LOGIN_PATTERN = "^[\\w-]{1,20}$";
    private static final String EMAIL_PATTERN = "\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,6}";
    private static final String PASSWORD_PATTERN = "([\\w]{6,12})";
    private static final String PHONE_PATTERN = "^[0-9]{7,15}$";

    private static Pattern pattern;
    private static Matcher matcher;

    private boolean validate(final String value, final String template) {
        if (value != null) {
            pattern = Pattern.compile(template);
            matcher = pattern.matcher(value);
            return matcher.find();
        }
        return false;
    }

    public boolean validateForPositiveInteger(final int id) {
        return id > 0;
    }

    /**
     * Validates login to match regexp for login.
     *
     * @param login String
     * @return result
     */
    public boolean validateLogin(final String login) {
        return validate(login, LOGIN_PATTERN);
    }

    /**
     * Validates email to match regexp for email.
     *
     * @param email String
     * @return result
     */
    public boolean validateEmail(final String email) {
        return validate(email, EMAIL_PATTERN);
    }

    /**
     * Validates password to match regexp for password.
     *
     * @param password String
     * @return result
     */
    public boolean validatePassword(final String password) {
        return validate(password, PASSWORD_PATTERN);
    }

    /**
     * Validates phone to match regexp for phone.
     *
     * @param phone String
     * @return result
     */
    public boolean validatePhone(final String phone) {
        return validate(phone, PHONE_PATTERN);
    }

}
