/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.UserData.UserDetails;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Test for methods in UserDataValidator
 * 
 * @author Ujjwal Dhakal 12222900
 */
public class UserDataValidatorTest {
    
    private final UserDataValidator validator = new UserDataValidator(null); // No DB access required in Phase 5

    /**
     * Test: both username and password are empty.
     */
    @Test
    public void testCheckForFieldsPresentBothEmpty() {
        ValidationResponse result = validator.checkForFieldsPresent("", "");
        assertFalse(result.valid());
        assertEquals("Username must not be empty.", result.message());
    }

    /**
     * Test: password is missing.
     */
    @Test
    public void testCheckForFieldsPresentPasswordEmpty() {
        ValidationResponse result = validator.checkForFieldsPresent("admin", "");
        assertFalse(result.valid());
        assertEquals("Password must not be empty.", result.message());
    }

    /**
     * Test: both fields are filled.
     */
    @Test
    public void testCheckForFieldsPresentValid() {
        ValidationResponse result = validator.checkForFieldsPresent("admin", "password");
        assertTrue(result.valid());
        assertEquals("Fields present.", result.message());
    }

    /**
     * Test: user found and password is default.
     */
    @Test
    public void testCheckCurrentDetailsValidDefaultPassword() {
        UserDetails user = new UserDetails("admin", "password");
        ValidationResponse result = validator.checkCurrentDetails(user, "admin", "password");
        assertTrue(result.valid());
        assertEquals("Please change your password.", result.message());
    }

    /**
     * Test: user object is null.
     */
    @Test
    public void testCheckCurrentDetailsUserNotFound() {
        ValidationResponse result = validator.checkCurrentDetails(null, "admin", "password");
        assertFalse(result.valid());
        assertEquals("User not found.", result.message());
    }

    /**
     * Test: username doesn't match.
     */
    @Test
    public void testCheckCurrentDetailsWrongUsername() {
        UserDetails user = new UserDetails("admin", "password");
        ValidationResponse result = validator.checkCurrentDetails(user, "wrong", "password");
        assertFalse(result.valid());
        assertEquals("Username is incorrect.", result.message());
    }

    /**
     * Test: password is wrong.
     */
    @Test
    public void testCheckCurrentDetailsWrongPassword() {
        UserDetails user = new UserDetails("admin", "password");
        ValidationResponse result = validator.checkCurrentDetails(user, "admin", "wrongpass");
        assertFalse(result.valid());
        assertEquals("Password is incorrect.", result.message());
    }

    /**
     * Test: password is correct but not default.
     */
    @Test
    public void testCheckCurrentDetailsNonDefaultPassword() {
        UserDetails user = new UserDetails("admin", "SecurePass123!");
        ValidationResponse result = validator.checkCurrentDetails(user, "admin", "SecurePass123!");
        assertFalse(result.valid());
        assertEquals("Password checking still under development.", result.message());
    }
}

