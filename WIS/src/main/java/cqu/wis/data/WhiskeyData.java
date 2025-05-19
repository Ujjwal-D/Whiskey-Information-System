/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to Whiskey database and retrievs the information
 * Connects to the WHISKEY database and uses prepared statements.
 *
 * @author Ujjwal Dhakal 12222900
 */

public class WhiskeyData {
    private Connection connection; // Connection to the database
    private PreparedStatement psAllMalts; // To get all records
    private PreparedStatement psMaltsFromRegion; // To get records by region
    private PreparedStatement psMaltsInAgeRange; // To get records by age range

    /**
     * Represents a whiskey record.
     * Each record has a distillery name, age, region, and price.
     */
    public record WhiskeyDetails(String distillery, int age, String region, int price) {}

    /**
     * Constructor for WhiskeyData 
     */
    public WhiskeyData() {}

    /**
     * Connects to the WHISKEY database.
     * Also prepares SQL statements for reuse.
     */
    public void connect() {
        try {
            // Open connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/WHISKEY", "root", "admin");

            // Prepare SQL statements 
            psAllMalts = connection.prepareStatement("SELECT * FROM SINGLEMALTS");
            psMaltsFromRegion = connection.prepareStatement("SELECT * FROM SINGLEMALTS WHERE REGION = ?");
            psMaltsInAgeRange = connection.prepareStatement("SELECT * FROM SINGLEMALTS WHERE AGE BETWEEN ? AND ?");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0); // Stop program on failure
        }
    }

    /**
     * Closes the database connection safely.
     */
    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Gets all whiskey records from the database.
     * @return list of all whiskey records
     */
    public List<WhiskeyDetails> getAllMalts() {
        List<WhiskeyDetails> list = new ArrayList<>();
        try (ResultSet rs = psAllMalts.executeQuery()) {
            // Go through all results + add them to the list
            while (rs.next()) {
                list.add(new WhiskeyDetails(
                    rs.getString("DISTILLERY"),
                    rs.getInt("AGE"),
                    rs.getString("REGION"),
                    rs.getInt("PRICE")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    /**
     * Gets whiskey records by region name.
     * @param region name of the region to filter
     * @return list of matching whiskey records
     */
    public List<WhiskeyDetails> getMaltsFromRegion(String region) {
        List<WhiskeyDetails> list = new ArrayList<>();
        try {
            psMaltsFromRegion.setString(1, region);
            ResultSet rs = psMaltsFromRegion.executeQuery();
            while (rs.next()) { 
                list.add(new WhiskeyDetails(
                    rs.getString("DISTILLERY"),
                    rs.getInt("AGE"),
                    rs.getString("REGION"),
                    rs.getInt("PRICE")
                )); // details for every whiskey
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }

    /**
     * Gets whiskey records between two age values.
     * @param lower minimum age
     * @param upper maximum age
     * @return list of whiskeys in the given age range
     */
    public List<WhiskeyDetails> getMaltsInAgeRange(int lower, int upper) {
        List<WhiskeyDetails> list = new ArrayList<>();
        try {
            psMaltsInAgeRange.setInt(1, lower);
            psMaltsInAgeRange.setInt(2, upper);
            ResultSet rs = psMaltsInAgeRange.executeQuery();
            while (rs.next()) {
                list.add(new WhiskeyDetails(
                    rs.getString("DISTILLERY"),
                    rs.getInt("AGE"),
                    rs.getString("REGION"),
                    rs.getInt("PRICE")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
}













    





