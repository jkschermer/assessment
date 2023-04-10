package com.example.assessment.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHashing {
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    public static boolean verifyPassword(String inputtedPassword, String storedPassword) throws NoSuchAlgorithmException {
        String hashedInputtedPassword = hashPassword(inputtedPassword);
        return hashedInputtedPassword.equals(storedPassword);
    }
}
