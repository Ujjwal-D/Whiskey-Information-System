/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.data;

import java.sql.*;
import java.util.Optional;

/**
 * Represents the user data access layer for authentication.
 * @author Ujjwal
 */
public class UserData {
    private Connection connection;
    private PreparedStatement psFindUser;

    private final String FIND_USER_QUERY = "SELECT * FROM PASSWORDS WHERE USERNAME = ?";

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
    
}
