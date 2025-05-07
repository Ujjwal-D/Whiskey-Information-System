/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.WhiskeyData;
import cqu.wis.data.WhiskeyData.WhiskeyDetails;
import java.util.List;

/**
 * Manages whiskey data records retrieved from the database.
 * Maintains current index and provides navigation support.
 * 
 * @author Ujjwal Dhakal 12222900
 */
public class WhiskeyDataManager {

    private WhiskeyData data;
    private List<WhiskeyDetails> records;
    private int currentIndex;

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
        data.connect();
        data.initialise();
    }

    /**
     * Closes database connection.
     */
    public void disconnect() {
        data.disconnect();
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
        if (records == null || records.isEmpty()) {
            throw new IllegalStateException("No whiskey records loaded. Call findAllMalts() first.");
        }
        if (currentIndex < records.size() - 1) {
            currentIndex++;
        }
        return records.get(currentIndex);
    }

    /**
     * Returns the previous whiskey record.
     * If already at the start, returns the first record.
     * @return WhiskeyDetails object
     */
    public WhiskeyDetails previous() {
        if (records == null || records.isEmpty()) {
            throw new IllegalStateException("No whiskey records loaded. Call findAllMalts() first.");
        }
        if (currentIndex > 0) {
            currentIndex--;
        }
        return records.get(currentIndex);
    }
}

