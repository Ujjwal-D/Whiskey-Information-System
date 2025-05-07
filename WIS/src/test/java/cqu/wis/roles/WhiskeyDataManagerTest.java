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

    @Test
    public void nextWithNoRecordsTest() {
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        WhiskeyData.WhiskeyDetails[] details = {};
        wdm.setDetails(details);
        assertNull(wdm.next());
    }

    @Test
    public void nextWithOneRecordTest() {
        WhiskeyData.WhiskeyDetails d1 = new WhiskeyData.WhiskeyDetails("Lagavulin", 16, "Islay", 100);
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[]{d1});
        assertEquals(d1, wdm.next());
        assertEquals(d1, wdm.next());
    }

    @Test
    public void nextWithTwoRecordsCyclesCorrect() {
        WhiskeyData.WhiskeyDetails d1 = new WhiskeyData.WhiskeyDetails("Lagavulin", 16, "Islay", 100);
        WhiskeyData.WhiskeyDetails d2 = new WhiskeyData.WhiskeyDetails("Oban", 14, "Highland", 120);

        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[]{d1, d2});

        assertEquals(d1, wdm.next());
        assertEquals(d2, wdm.next());
        assertEquals(d1, wdm.next());
    }

    @Test
    public void previousWithNoRecordsTest() {
        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        WhiskeyData.WhiskeyDetails[] details = {};
        wdm.setDetails(details);
        assertNull(wdm.previous());
    }

    @Test
    public void previousWithTwoRecordsCyclesCorrect() {
        WhiskeyData.WhiskeyDetails d1 = new WhiskeyData.WhiskeyDetails("Lagavulin", 16, "Islay", 100);
        WhiskeyData.WhiskeyDetails d2 = new WhiskeyData.WhiskeyDetails("Oban", 14, "Highland", 120);

        WhiskeyDataManager wdm = new WhiskeyDataManager(null);
        wdm.setDetails(new WhiskeyData.WhiskeyDetails[]{d1, d2});

        assertEquals(d2, wdm.previous());
        assertEquals(d1, wdm.previous());
        assertEquals(d2, wdm.previous());
    }
    
    // Part 2D: Query Test

    /**
     * Tests findMaltsFromRegion with no matching region.
     */
    @Test
    public void testFindMaltsFromRegionNoMatches() {
        WhiskeyData wd = new WhiskeyData();
        wd.connect();
        wd.initialise();

        WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
        int count = wdm.findMaltsFromRegion("UnknownRegion");

        assertEquals(0, count);
        assertNull(wdm.next());
        wd.disconnect();
    }

    /**
     * Tests findMaltsFromRegion with known region (e.g. Islay).
     */
    @Test
    public void testFindMaltsFromRegionKnownRegion() {
        WhiskeyData wd = new WhiskeyData();
        wd.connect();
        wd.initialise();

        WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
        int count = wdm.findMaltsFromRegion("Islay");

        assertTrue(count > 0);
        WhiskeyData.WhiskeyDetails first = wdm.first();
        assertNotNull(first);
        assertEquals("Islay", first.region());
        wd.disconnect();
    }

    /**
     * Tests findMaltsInAgeRange with range that has results.
     */
    @Test
    public void testFindMaltsInAgeRangeValidRange() {
        WhiskeyData wd = new WhiskeyData();
        wd.connect();
        wd.initialise();

        WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
        int count = wdm.findMaltsInAgeRange(10, 15);

        assertTrue(count > 0);
        assertNotNull(wdm.first());
        wd.disconnect();
    }

    /**
     * Tests findMaltsInAgeRange with a range that returns no results.
     */
    @Test
    public void testFindMaltsInAgeRangeNoResults() {
        WhiskeyData wd = new WhiskeyData();
        wd.connect();
        wd.initialise();

        WhiskeyDataManager wdm = new WhiskeyDataManager(wd);
        int count = wdm.findMaltsInAgeRange(1000, 2000);

        assertEquals(0, count);
        assertNull(wdm.next());
        wd.disconnect();
    }
}

