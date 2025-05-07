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
}
