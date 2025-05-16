/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cqu.wis.roles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for WhiskeyDataValidator methods like checkAgeRAnge and checkRegion.
 * 
 * @author Ujjwal Dhakal 12222900
 */

public class WhiskeyDataValidatorTest {

    WhiskeyDataValidator validator = new WhiskeyDataValidator();

    /** Test valid age range like 10 to 20 */
    @Test
    public void checkAgeRangeValid() {
        var result = validator.checkAgeRange("10", "20");
        assertTrue(result.valid());
        assertEquals(10, result.range().lower());
        assertEquals(20, result.range().upper());
    }

    /** Test invalid input when lower age is not a number */
    @Test
    public void checkAgeRangeNonNumeric() {
        var result = validator.checkAgeRange("ten", "20");
        assertFalse(result.valid());
    }

    /** Test invalid input when lower > upper */
    @Test
    public void checkAgeRangeLowerGreaterThanUpper() {
        var result = validator.checkAgeRange("30", "20");
        assertFalse(result.valid());
    }

    /** Test empty age input */
    @Test
    public void checkAgeRangeEmptyInput() {
        var result = validator.checkAgeRange("", "20");
        assertFalse(result.valid());
    }

    /** Test valid region input */
    @Test
    public void checkRegionValid() {
        var result = validator.checkRegion("Islay");
        assertTrue(result.valid());
    }

    /** Test when region is empty string */
    @Test
    public void checkRegionEmpty() {
        var result = validator.checkRegion("");
        assertFalse(result.valid());
    }

    /** Test when region is null */
    @Test
    public void checkRegionNull() {
        var result = validator.checkRegion(null);
        assertFalse(result.valid());
    }
}

