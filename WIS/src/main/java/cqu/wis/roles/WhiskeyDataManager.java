/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.WhiskeyData;
import cqu.wis.data.WhiskeyData.WhiskeyDetails;
import java.util.*;

/**
 * Manages whiskey records and navigation for the GUI.
 * 
 * 
 */
public class WhiskeyDataManager {

    private WhiskeyData wd;
    private List<WhiskeyData.WhiskeyDetails> records;
    private int numberOfRecords;
    private int currentIndex;
    private WhiskeyData.WhiskeyDetails currentRecord;

    /**
     * Constructor: accepts a reference to WhiskeyData.
     * @param wd the data access object
     */
    public WhiskeyDataManager(WhiskeyData wd) {
        this.wd = wd;
        this.records = new ArrayList<>();
        this.numberOfRecords = 0;
        this.currentIndex = -1;
        this.currentRecord = null;
    }

    /**
     * For testing: sets records manually.
     * @param details array of WhiskeyDetails
     */
    public void setDetails(WhiskeyData.WhiskeyDetails[] details) {
        List<WhiskeyDetails> list = Arrays.asList(details);
        this.records = new ArrayList<>(list);
        this.numberOfRecords = records.size();
        this.currentIndex = -1;
        this.currentRecord = null;
    }

    /**
     * Loads all malt records from the database.
     * @return number of records loaded
     */
    public int findAllMalts() {
        if (wd == null) return 0;

        this.records = wd.getAllMalts();
        this.numberOfRecords = records.size();
        this.currentIndex = (numberOfRecords == 0) ? -1 : 0;
        this.currentRecord = (numberOfRecords == 0) ? null : records.get(0);
        return numberOfRecords;
    }
    
    /**
    * Loads all malts from a given region into memory.
    * @param region region to filter by
    * @return number of records found
    */
   public int findMaltsFromRegion(String region) {
       records = wd.getMaltsFromRegion(region);
       numberOfRecords = records.size();
       currentIndex = (numberOfRecords == 0) ? -1 : 0;
       currentRecord = (numberOfRecords == 0) ? null : records.get(0);
       return numberOfRecords;
   }
   
   /**
    * Loads all malts within the given age range into memory.
    * @param lower lower age bound
    * @param upper upper age bound
    * @return number of records found
    */
   public int findMaltsInAgeRange(int lower, int upper) {
       records = wd.getMaltsInAgeRange(lower, upper);
       numberOfRecords = records.size();
       currentIndex = (numberOfRecords == 0) ? -1 : 0;
       currentRecord = (numberOfRecords == 0) ? null : records.get(0);
       return numberOfRecords;
   }

    /**
     * Returns the first record.
     * @return first WhiskeyDetails, or null
     */
    public WhiskeyDetails first() {
        if (numberOfRecords == 0) return null;
        currentIndex = 0;
        currentRecord = records.get(currentIndex);
        return currentRecord;
    }

    /**
     * Returns the next record, cycling if needed.
     * @return next WhiskeyDetails, or null
     */
    public WhiskeyDetails next() {
        if (numberOfRecords==0) {
            return null;
        }
        if (currentIndex == -1) {
            currentIndex = 0;
        } else {
            currentIndex = (currentIndex + 1) % numberOfRecords;
        }

        currentRecord = records.get(currentIndex);
        return currentRecord;
    }

    /**
     * Returns the previous record, cycling if needed.
     * @return previous WhiskeyDetails, or null
     */
    
    public WhiskeyDetails previous() {
        if (numberOfRecords == 0) return null;

        // First time calling previous(): go to last record
        if (currentIndex == -1) {
            currentIndex = numberOfRecords - 1;
        } else {
            currentIndex = (currentIndex - 1 + numberOfRecords) % numberOfRecords;
        }

        currentRecord = records.get(currentIndex);
        return currentRecord;
    }
}

