/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.WhiskeyData;
import cqu.wis.data.WhiskeyData.WhiskeyDetails;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Manages whiskey data records retrieved from the database.
 * Maintains current index and provides navigation support.
 * 
 * @author Ujjwal Dhakal 12222900
 */
public class WhiskeyDataManager {

    private WhiskeyData data;   // object creation
    private List<WhiskeyDetails> records;   // holds records
    private int numberOfRecords;    //counts number of records
    private int currentIndex;   //holds count of current index
    private WhiskeyDetails currentRecord;   //holds current record

    /**
     * Constructor assigns a reference to the data access object.
     * @param data WhiskeyData object
     */
    public WhiskeyDataManager(WhiskeyData data) {
        this.data = data;
    }

    /**
     * Opens database connection and prepares statements.
     */
    public void connect() {
        data.connect(); // connection 
        data.initialise();  // initialisation
    }

    /**
     * Closes database connection.
     */
    public void disconnect() {
        data.disconnect();  // disconnection
    }

    /**
     * Loads all malt whiskey records from the database.
     * @return number of records retrieved
     */
    public int findAllMalts() {
        records = data.getAllMalts();
        currentIndex = 0;
        return records.size();
    }

    /**
     * Returns the first whiskey record.
     * @return WhiskeyDetails object
     */
    public WhiskeyDetails first() {
        currentIndex = 0;
        return records.get(currentIndex);
    }

    /**
     * Returns the next whiskey record.
     * If already at the end, returns the last record.
     * @return WhiskeyDetails object
     */
    public WhiskeyDetails next() {
        if (records == null || records.isEmpty()) return null;
        
        currentIndex = (currentIndex + 1) % numberOfRecords;
        currentRecord = records.get(currentIndex);
        return currentRecord;
    }

    /**
     * Returns the previous whiskey record.
     * If already at the start, returns the first record.
     * 
     * @return WhiskeyDetails object
     */
    public WhiskeyDetails previous() {
        if (records == null || records.isEmpty()) return null;
        
        
        currentIndex = (currentIndex - 1 + numberOfRecords) % numberOfRecords;
        currentRecord = records.get(currentIndex);
        return currentRecord;
    }
    
    /**
    * Sets whiskey details manually for unit testing purposes.
    *
    * @param details an array of WhiskeyDetails to load
    */
   public void setDetails(WhiskeyData.WhiskeyDetails[] details) {
       List<WhiskeyData.WhiskeyDetails> list = Arrays.asList(details);
       records = new ArrayList<>(list);
       numberOfRecords = records.size();
       currentIndex = (numberOfRecords == 0) ? -1 : -1; //  no record selected at first
       currentRecord = null;
   }

}

