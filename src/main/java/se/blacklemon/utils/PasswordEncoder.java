package se.blacklemon.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

    public String encode(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        /* MessageDigest instance for MD5. */
        messageDigest.update(password.getBytes());

        /* Convert the hash value into bytes */
        byte[] bytes = messageDigest.digest();

        /* The bytes array has bytes in decimal form. Converting it into hexadecimal format. */
        StringBuilder s = new StringBuilder();

        for (byte aByte : bytes) {
            s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }

        /* Complete hashed password in hexadecimal format */
        return s.toString();
    }
}
