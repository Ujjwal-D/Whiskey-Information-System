/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.UserData.UserDetails;
import java.util.Optional;

/**
 * Validates user input for login and password change.
 * @author Ujjwal
 */
public class UserDataValidator {

    private UserDataManager udm;

    /**
     * Constructor without arguments.
     */
    public UserDataValidator(){
        
    }
    
    /**
     * Constructor that receives a reference to UserDataManager.
     */
    public UserDataValidator(UserDataManager udm) {
        this.udm = udm;
    }


    /**
     * Checks that both username and password are filled in.
     * @param username the input username
     * @param password the input password
     * @return ValidationResponse indicating missing fields or success
     */
    public ValidationResponse checkForFieldsPresent(String username, String password) {
        if (username == null || username.isBlank()) {
            return new ValidationResponse(false, "Username must not be empty.");
        }
        if (password == null || password.isBlank()) {
            return new ValidationResponse(false, "Password must not be empty.");
        }
        return new ValidationResponse(true, "Fields present.");
    }

    /**
     * Checks whether the user exists and is still using the default password.
     * @param user UserDetails record (from DB)
     * @param username input username
     * @param password input password
     * @return ValidationResponse with result and message
     */
    public ValidationResponse checkCurrentDetails(UserDetails user, String username, String password) {
        if (user == null) {
            return new ValidationResponse(false, "User not found.");
        }
        if (!user.username().equals(username)) {
            return new ValidationResponse(false, "Username is incorrect.");
        }
        if (!user.password().equals(password)) {
            return new ValidationResponse(false, "Password is incorrect.");
        }
        if (user.password().equals("password")) {
            return new ValidationResponse(true, "Please change your password.");
        }
        return new ValidationResponse(false, "Password checking still under development.");
    }
} 