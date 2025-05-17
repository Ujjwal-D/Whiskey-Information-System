/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.UserData;
import cqu.wis.data.UserData.UserDetails;
import java.util.Optional;

/**
 * Handles user-related data operations.
 * @author Ujjwal
 */

public class UserDataManager {

    private UserData ud;

    /**
     * Constructs the manager with a reference to UserData.
     */
    public UserDataManager(UserData ud) {
        this.ud = ud;
    }
    
    /**
    * Finds a user by username using UserData.
    * @param username the entered username
    * @return UserDetails if found
    */
   public UserDetails findUser(String username) {
       return ud.findUser(username);
   }
}