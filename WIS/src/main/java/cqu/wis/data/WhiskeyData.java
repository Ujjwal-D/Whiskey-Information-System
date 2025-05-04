/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ujjwal Dhakal 12222900
 */
public class WhiskeyData {
    
    public record WhiskeyDetails(String distillery, int age, String region, int price){}

    // Instance variables
    private Connection connection;
    private PreparedStatement selectAllMaltsStatement;

    // Constructor
    public WhiskeyData() {
    }

    // Establishes DB connection and prepares the SELECT statement
    public void connect() {
        try {
            // Replace with actual database details
            String url = "jdbc:mysql://localhost:3306/WHISKEY";
            String user = "admin";
            String password = "admin";

            // Connect to database
            connection = DriverManager.getConnection(url, user, password);

            // Prepare the SELECT ALL MALTS statement
            String sql = "SELECT distillery, age, region, price FROM SINGLEMALTS";
            selectAllMaltsStatement = connection.prepareStatement(sql);

        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

        // Closes the prepared statement and connection
    public void disconnect() {
        try {
            if (selectAllMaltsStatement != null && !selectAllMaltsStatement.isClosed()) {
                selectAllMaltsStatement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error disconnecting from database: " + e.getMessage());
        }
    }
    
    // Executes the prepared SELECT statement and returns list of whiskey records
    public List<WhiskeyDetails> getAllMalts() {
        List<WhiskeyDetails> list = new ArrayList<>();
        try (ResultSet resultSet = selectAllMaltsStatement.executeQuery()) {
            while (resultSet.next()) {
                String distillery = resultSet.getString("distillery");
                int age = resultSet.getInt("age");
                String region = resultSet.getString("region");
                int price = resultSet.getInt("price");

                list.add(new WhiskeyDetails(distillery, age, region, price));
            }
        } catch (SQLException e) {
            System.err.println("Error executing getAllMalts: " + e.getMessage());
        }
        return list;
    }
}













    





