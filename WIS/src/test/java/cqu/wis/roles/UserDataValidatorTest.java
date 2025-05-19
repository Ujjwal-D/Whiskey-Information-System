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
        UserDetails user = new UserDetails("admin", validator.generateSHA1("SecurePass123!"));
        ValidationResponse result = validator.checkCurrentDetails(user, "admin", "SecurePass123!");
        assertTrue(result.valid());
        assertEquals("Login successful.", result.message());
    }
    
    /**
     * Test: encrypted password is wrong.
     */
    @Test
    public void testCheckCurrentDetailsEncryptedPasswordWrong() {
        UserDetails user = new UserDetails("admin", validator.generateSHA1("Correct123!"));
        ValidationResponse result = validator.checkCurrentDetails(user, "admin", "Wrong123!");
        assertFalse(result.valid());
        assertEquals("Password is incorrect.", result.message());
    }
    
    /**
     * Test: username field is empty.
     */
    @Test
    public void testCheckCurrentDetailsEnteredUsernameEmpty() {
        UserDetails user = new UserDetails("admin", "password");
        ValidationResponse result = validator.checkCurrentDetails(user, "", "password");
        assertFalse(result.valid());
        assertEquals("Username is incorrect.", result.message());
    }

    /**
     * Test: All three fields missing for change password.
     */
    @Test
    public void testCheckForFieldsPresentThreeEmpty() {
        ValidationResponse result = validator.checkForFieldsPresent("", "", "");
        assertFalse(result.valid());
        assertEquals("Username must not be empty.", result.message());
    }

    /**
     * Test: New password field missing.
     */
    @Test
    public void testCheckForFieldsPresentNewPasswordMissing() {
        ValidationResponse result = validator.checkForFieldsPresent("admin", "old", "");
        assertFalse(result.valid());
        assertEquals("New password must not be empty.", result.message());
    }

    /**
     * Test: All three fields provided.
     */
    @Test
    public void testCheckForFieldsPresentThreeValid() {
        ValidationResponse result = validator.checkForFieldsPresent("admin", "old", "newPass123!");
        assertTrue(result.valid());
        assertEquals("All fields present.", result.message());
    }

    /**
     * Test: new password same as old password.
     */
    @Test
    public void testCheckNewDetailsSamePassword() {
        UserDetails user = new UserDetails("admin", validator.generateSHA1("password123!"));
        ValidationResponse result = validator.checkNewDetails(user, "admin", "password123!", "password123!");
        assertFalse(result.valid());
        assertEquals("New password cannot be the same as the old password.", result.message());
    }

    /**
     * Test: new password weak (no special char, short).
     */
    @Test
    public void testCheckNewDetailsWeakPassword() {
        UserDetails user = new UserDetails("admin", validator.generateSHA1("Oldpass123!"));
        ValidationResponse result = validator.checkNewDetails(user, "admin", "Oldpass123!", "short");
        assertFalse(result.valid());
        assertEquals("Password must be at least 8 characters long.", result.message());
    }


    /**
     * Test: valid password change.
     */
    @Test
    public void testCheckNewDetailsValidPasswordChange() {
        UserDetails user = new UserDetails("admin", validator.generateSHA1("OldPass123!"));
        ValidationResponse result = validator.checkNewDetails(user, "admin", "OldPass123!", "NewPass123!");
        assertTrue(result.valid());
        assertEquals("New password is valid.", result.message());
    }

    /**
     * Test: missing uppercase letter in new password
     */    
     @Test
    public void testCheckNewDetailsMissingUppercase() {
        UserDetails user = new UserDetails("admin", validator.generateSHA1("Oldpass123!"));
        ValidationResponse result = validator.checkNewDetails(user, "admin", "Oldpass123!", "newpass123!");
        assertFalse(result.valid());
        assertEquals("Password must contain at least one uppercase letter.", result.message());
    }

    /**
     * Test: missing lowercase letter in new password
     */    
    @Test
    public void testCheckNewDetailsMissingLowercase() {
        UserDetails user = new UserDetails("admin", validator.generateSHA1("Oldpass123!"));
        ValidationResponse result = validator.checkNewDetails(user, "admin", "Oldpass123!", "NEWPASS123!");
        assertFalse(result.valid());
        assertEquals("Password must contain at least one lowercase letter.", result.message());
    }

    /**
     * Test: missing numbers in new password
     */    
    @Test
    public void testCheckNewDetailsMissingNumber() {
        UserDetails user = new UserDetails("admin", validator.generateSHA1("Oldpass123!"));
        ValidationResponse result = validator.checkNewDetails(user, "admin", "Oldpass123!", "Newpass!!");
        assertFalse(result.valid());
        assertEquals("Password must contain at least one number.", result.message());
    }
    
    /**
     * Test: missing special characters in new password
     */
    @Test
    public void testCheckNewDetailsMissingSpecialChar() {
        UserDetails user = new UserDetails("admin", validator.generateSHA1("Oldpass123!"));
        ValidationResponse result = validator.checkNewDetails(user, "admin", "Oldpass123!", "Newpass123");
        assertFalse(result.valid());
        assertEquals("Password must contain at least one special character.", result.message());
    }

    /**
     * Test: the username is not registered.
     */    
    @Test
    public void testCheckNewDetailsUsernameMismatch() {
        UserDetails user = new UserDetails("admin", validator.generateSHA1("Oldpass123!"));
        ValidationResponse result = validator.checkNewDetails(user, "user2", "Oldpass123!", "Newpass123!");
        assertFalse(result.valid());
        assertEquals("Username is incorrect.", result.message());
    }
}

