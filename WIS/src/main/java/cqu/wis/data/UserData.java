/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.data;

import java.sql.*;


/**
 * Represents the user data access layer for authentication.
 * @author Ujjwal Dhakal 12222900
 */
public class UserData {
    private Connection connection;
    private PreparedStatement psFindUser;

    private final String FIND_USER_QUERY = "SELECT * FROM PASSWORDS WHERE USERNAME = ?";

    /**
     * Establishes connection to the database
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/USERS", "root", "admin");
            psFindUser = connection.prepareStatement(FIND_USER_QUERY);
        } catch (SQLException e) {
            System.err.println("Connection Error: " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Represents user login details.
     */
    public record UserDetails(String username, String password) {}

    /**
     * Finds user by username.
     * @param username entered username
     * @return Optional of UserDetails if found
     */
    public UserDetails findUser(String username) {
        try {
            psFindUser.setString(1, username);
            ResultSet rs = psFindUser.executeQuery();
            if (rs.next()) {
                return new UserDetails(
                    rs.getString("USERNAME"),
                    rs.getString("PASSWORD")
                );
            }
        } catch (SQLException e) {
            System.err.println("findUser Error: " + e.getMessage());
        }
        return null;
    }
    
    /**
    * Updates the password for a given username in the database.
    * @param username the username whose password is to be updated
    * @param newPassword the new encrypted password
    * @return true if update was successful, false otherwise
    */
   public boolean updatePassword(String username, String newPassword) {
       String updateQuery = "UPDATE PASSWORDS SET PASSWORD = ? WHERE USERNAME = ?";
       try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
           ps.setString(1, newPassword);
           ps.setString(2, username);
           int rows = ps.executeUpdate();
           return rows == 1;
       } catch (SQLException e) {
           System.err.println("Update Error: " + e.getMessage());
           return false;
       }
   }
    
}
