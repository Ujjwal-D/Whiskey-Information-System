/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cqu.wis.roles;

import cqu.wis.data.WhiskeyData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for WhiskeyDataManager's next() and previous() methods.
 * 
 * @author Ujjwal Dhakal 12222900
 */

public class WhiskeyDataManagerTest {

    /** 
     * Test next() with no records set
     */
    @Test
    public void nextWithNoRecordsTest() {
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[] {});
        assertNull(wdm.next());
    }

    /** 
     * Test next() with one record (should cycle on itself)
      */
    @Test
    public void nextWithOneRecordTest() {
        WhiskeyData.WhiskeyDetails d = new WhiskeyData.WhiskeyDetails("Laphroaig", 10, "Islay", 100);
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[]{d});
        assertEquals(d, wdm.next());
        assertEquals(d, wdm.next());
    }

    /** 
     * Test next() with two records going in order 
     */
    @Test
    public void nextWithTwoRecordsTest() {
        WhiskeyData.WhiskeyDetails d1 = new WhiskeyData.WhiskeyDetails("Lagavulin", 16, "Islay", 110);
        WhiskeyData.WhiskeyDetails d2 = new WhiskeyData.WhiskeyDetails("Oban", 14, "Highland", 120);
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[]{d1, d2});
        assertEquals(d1, wdm.next());
        assertEquals(d2, wdm.next());
    }

    /** 
     * Test next() with two records wrapping around 
     */
    @Test
    public void nextWithTwoRecordsCyclesTest() {
        WhiskeyData.WhiskeyDetails d1 = new WhiskeyData.WhiskeyDetails("Lagavulin", 16, "Islay", 110);
        WhiskeyData.WhiskeyDetails d2 = new WhiskeyData.WhiskeyDetails("Oban", 14, "Highland", 120);
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[]{d1, d2});
        wdm.next(); // d1
        wdm.next(); // d2
        assertEquals(d1, wdm.next());
    }

    /** 
     * Test previous() with no records set 
     */
    @Test
    public void previousWithNoRecordsTest() {
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[] {});
        assertNull(wdm.previous());
    }

    /** 
     * Test previous() with one record (should cycle on itself) 
     */
    @Test
    public void previousWithOneRecordTest() {
        WhiskeyData.WhiskeyDetails d = new WhiskeyData.WhiskeyDetails("Tomatin", 12, "Highland", 90);
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[]{d});
        assertEquals(d, wdm.previous());
        assertEquals(d, wdm.previous());
    }

    /** 
     * Test previous() with two records going backwards 
     */
    @Test
    public void previousWithTwoRecordsTest() {
        WhiskeyData.WhiskeyDetails d1 = new WhiskeyData.WhiskeyDetails("Lagavulin", 16, "Islay", 110);
        WhiskeyData.WhiskeyDetails d2 = new WhiskeyData.WhiskeyDetails("Oban", 14, "Highland", 120);
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[]{d1, d2});
        assertEquals(d2, wdm.previous());
        assertEquals(d1, wdm.previous());
    }

    /** 
     * Test previous() with two records wrapping around 
     */
    @Test
    public void previousWithTwoRecordsCyclesTest() {
        WhiskeyData.WhiskeyDetails d1 = new WhiskeyData.WhiskeyDetails("Lagavulin", 16, "Islay", 110);
        WhiskeyData.WhiskeyDetails d2 = new WhiskeyData.WhiskeyDetails("Oban", 14, "Highland", 120);
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[]{d1, d2});
        wdm.previous(); // d2
        wdm.previous(); // d1
        assertEquals(d2, wdm.previous());
    }
}
