/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.data;

/**
 * Represents the user data access layer for authentication.
 * @author Ujjwal
 */
public class UserData {
    
     /**
     * Record to store user credentials.
     * @param user username
     * @param password password
     */
    public record UserDetails(String user, String password) {}
    
    /**
     * Connects to the USERS database. Placeholder for now.
     */
    public void connect() {
        // This will be implemented in later phases
    }
    
}