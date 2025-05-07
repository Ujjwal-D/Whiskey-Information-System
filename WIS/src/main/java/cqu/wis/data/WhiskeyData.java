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
    
    private Connection connection;
    private PreparedStatement psAllMalts;   // prepared statement for all malts
    private PreparedStatement psMaltsFromRegion;    // prepared statement for malts from region
    private PreparedStatement psMaltsInAgeRange;    // prepared statement for malts in age range


    // SQL query to fetch all malt records
    private final String allMaltsQuery = "SELECT * FROM SINGLEMALTS";
    
        /**
     * Record to represent whiskey details.
     * @param distillery name of the distillery
     * @param age age of the whiskey
     * @param region region of production
     * @param price price of the whiskey
     */
    public record WhiskeyDetails(String distillery, int age, String region, int price) {}


    /**
     * Empty constructor for WhiskeyData.
     */
    public WhiskeyData() {}

    /**
     * Establishes a connection to the WHISKEY database.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/WHISKEY", "root", "admin");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Initializes all required prepared statements using the current connection.
     */
    public void initialise() {
        try {
            psAllMalts = connection.prepareStatement(allMaltsQuery);
            psMaltsFromRegion = connection.prepareStatement("SELECT * FROM SINGLEMALTS WHERE REGION = ?");
            psMaltsInAgeRange = connection.prepareStatement("SELECT * FROM SINGLEMALTS WHERE AGE BETWEEN ? AND ?");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Closes the current database connection.
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
     * Retrieves all malt whiskey records from the database.
     * @return List of WhiskeyDetails objects.
     */
    public List<WhiskeyDetails> getAllMalts() {
        ArrayList<WhiskeyDetails> details = new ArrayList<>();
        try {
            ResultSet rs = psAllMalts.executeQuery();
            while (rs.next()) {
                details.add(new WhiskeyDetails(
                    rs.getString("DISTILLERY"),
                    rs.getInt("AGE"),
                    rs.getString("REGION"),
                    rs.getInt("PRICE")
                ));
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }
        return details;
    }
    
    /**
    * Retrieves malt whiskeys filtered by region.
    * @param region the region name
    * @return List of WhiskeyDetails in that region
    */
   public List<WhiskeyDetails> getMaltsFromRegion(String region) {
       ArrayList<WhiskeyDetails> list = new ArrayList<>();
       try {
           psMaltsFromRegion.setString(1, region);
           ResultSet rs = psMaltsFromRegion.executeQuery();
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
           System.err.println("Error in getMaltsFromRegion: " + e.getMessage());
       }
       return list;
   }

   /**
    * Retrieves malt whiskeys within the specified age range.
    * @param lower minimum age
    * @param upper maximum age
    * @return List of WhiskeyDetails within age range
    */
   public List<WhiskeyDetails> getMaltsInAgeRange(int lower, int upper) {
       ArrayList<WhiskeyDetails> list = new ArrayList<>();
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
           System.err.println("Error in getMaltsInAgeRange: " + e.getMessage());
       }
       return list;
   }
 
}















    





