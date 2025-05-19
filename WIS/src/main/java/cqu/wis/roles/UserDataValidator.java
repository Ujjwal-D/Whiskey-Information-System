/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.UserData.UserDetails;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Validates user input for login and password change.
 * @author Ujjwal Dhakal 12222900
 */
public class UserDataValidator {
    
    private static final int MINIMUM_PASSWORD_LENGTH = 8;
    private UserDataManager udm;

    /**
     * Constructor
     * @param udm UserDataManager object
     */
    public UserDataValidator(UserDataManager udm) {
        this.udm = udm;
    }

    /**
     * Generates encrypted value for new password input
     * @param password  password in input field
     * @return encrypted string
     */
    public static String generateSHA1(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] magnitude = md.digest(password.getBytes());
            int signum = 1;
            BigInteger value = new BigInteger(signum, magnitude);
            return value.toString(16);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

   /**
    * Checks that both username and password fields are filled.
    * 
    * @param username username as input
    * @param password password as input
    * @return ValidationResponse record
    */
    public ValidationResponse checkForFieldsPresent(String username, String password) {
        if (username == null || username.trim().isEmpty())
            return new ValidationResponse(false, "Username must not be empty.");
        if (password == null || password.trim().isEmpty())
            return new ValidationResponse(false, "Password must not be empty.");
        return new ValidationResponse(true, "Fields present.");
    }

    /**
     * Checks that username, old password, and new password fields are filled.
     * Used in the change password screen.
     * 
     * @param username  username from change password scene
     * @param oldPassword  old password from change password scene 
     * @param newPassword   new password from change password scene
     * @return record of type ValidationResponse
     */
    public ValidationResponse checkForFieldsPresent(String username, String oldPassword, String newPassword) {
        if (username == null || username.trim().isEmpty())
            return new ValidationResponse(false, "Username must not be empty.");
        if (oldPassword == null || oldPassword.trim().isEmpty())
            return new ValidationResponse(false, "Old password must not be empty.");
        if (newPassword == null || newPassword.trim().isEmpty())
            return new ValidationResponse(false, "New password must not be empty.");
        return new ValidationResponse(true, "All fields present.");
    }

    /**
     * Validates the provided credentials against a given UserDetails record.
     * Also ensures the username matches the stored one.
     * 
     * @param user  default user
     * @param enteredUsername   username input from login scene
     * @param enteredPassword   password input from login scene
     * @return record of type Validation response (true/false, message)
     */
    public ValidationResponse checkCurrentDetails(UserDetails user, String enteredUsername, String enteredPassword) {
        if (user == null)
            return new ValidationResponse(false, "User not found.");

        if (!user.username().equals(enteredUsername))
            return new ValidationResponse(false, "Username is incorrect.");

        if (user.password().equals("password") && enteredPassword.equals("password")) {
            return new ValidationResponse(true, "Please change your password.");
        }

        String encryptedInput = generateSHA1(enteredPassword);
        if (user.password().equals(encryptedInput)) {
            return new ValidationResponse(true, "Login successful.");
        }

        return new ValidationResponse(false, "Password is incorrect.");
    }

    /**
     * Validates whether a new password is acceptable.
     * Ensures it's different from the old one and meets complexity rules.
     * 
     * @param user  default user in database
     * @param username input from username field in change password scene
     * @param oldPassword   input from old password field in change password scene
     * @param newPassword   input from new password field in change password scene
     * @return record of type ValidationResponse (true/false, message).
     */
    public ValidationResponse checkNewDetails(UserDetails user, String username, String oldPassword, String newPassword) {
        if (user == null)
            return new ValidationResponse(false, "User not found.");

        if (!user.username().equals(username))
            return new ValidationResponse(false, "Username is incorrect.");

        String encryptedOld = generateSHA1(oldPassword);
        if (!(user.password().equals(oldPassword) || user.password().equals(encryptedOld)))
            return new ValidationResponse(false, "Old password is incorrect.");

        if (oldPassword.equals(newPassword))
            return new ValidationResponse(false, "New password cannot be the same as the old password.");

        if (newPassword.length() < MINIMUM_PASSWORD_LENGTH)
            return new ValidationResponse(false, "Password must be at least 8 characters long.");

        if (!Pattern.compile("[0-9]").matcher(newPassword).find())
            return new ValidationResponse(false, "Password must contain at least one number.");

        if (!Pattern.compile("[A-Z]").matcher(newPassword).find())
            return new ValidationResponse(false, "Password must contain at least one uppercase letter.");

        if (!Pattern.compile("[a-z]").matcher(newPassword).find())
            return new ValidationResponse(false, "Password must contain at least one lowercase letter.");

        if (!Pattern.compile("[^a-zA-Z0-9]").matcher(newPassword).find())
            return new ValidationResponse(false, "Password must contain at least one special character.");

        if (Pattern.compile("[\\\"']").matcher(newPassword).find())
            return new ValidationResponse(false, "Password must not contain quotes or backslashes.");

        return new ValidationResponse(true, "New password is valid.");
    }
} 