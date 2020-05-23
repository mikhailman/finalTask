package by.verishko.kefir.service.Impl;

import org.apache.logging.log4j.core.util.Assert;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class PasswordUtils {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int ITERATIONS = 10000;
    private static final int KEY_LENGTH = 256;
    private static String SALT;

    private static byte[] hash(final char[] password, final byte[] salt) {
        PBEKeySpec keySpec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("salt");
            return secretKeyFactory.generateSecret(keySpec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error hashing password: " + e.getMessage(), e);
        } finally {
            keySpec.clearPassword();
        }
    }

    static String getSalt() {
        StringBuilder stringBuilder = new StringBuilder(30);
        for (int i = 0; i < 30; i++) {
            stringBuilder.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(stringBuilder);
    }

    static String generateHashPassword(final String password, final String salt) {
        byte[] hashPassword = hash(password.toCharArray(), salt.getBytes());
        return Base64.getEncoder().encodeToString(hashPassword);
    }

    static boolean verifyUserPassword(final String currentPassword, final String securedPassword, final String salt) {
        boolean returnValue;
        String newSecuredPassword = generateHashPassword(currentPassword, salt);
        returnValue = newSecuredPassword.equalsIgnoreCase(securedPassword);
        return returnValue;
    }

    private PasswordUtils() {
    }
}
