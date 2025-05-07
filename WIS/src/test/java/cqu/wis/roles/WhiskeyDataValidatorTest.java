/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package cqu.wis.roles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for WhiskeyDataValidator using updated structure with Range and RangeValidationResponse.
 * 
 * @author Ujjwal Dhakal 12222900
 */
public class WhiskeyDataValidatorTest {

    /**
     * Tests a valid region.
     */
    @Test
    public void validRegionTest() {
        WhiskeyDataValidator validator = new WhiskeyDataValidator();
        ValidationResponse result = validator.checkRegion("Islay");
        assertTrue(result.valid());
    }

    /**
     * Tests an unknown/invalid region.
     */
    @Test
    public void invalidRegionTest() {
        WhiskeyDataValidator validator = new WhiskeyDataValidator();
        ValidationResponse result = validator.checkRegion("MiddleEarth");
        assertFalse(result.valid());
    }

    /**
     * Tests age range with valid integers having proper order.
     */
    @Test
    public void validAgeRangeTest() {
        WhiskeyDataValidator validator = new WhiskeyDataValidator();
        WhiskeyDataValidator.RangeValidationResponse result = validator.checkAgeRange("10", "20");
        assertTrue(result.valid());
        assertEquals(10, result.range().lower());
        assertEquals(20, result.range().upper());
    }

    /**
     * Tests age range where lower > upper.
     */
    @Test
    public void invalidAgeOrderTest() {
        WhiskeyDataValidator validator = new WhiskeyDataValidator();
        WhiskeyDataValidator.RangeValidationResponse result = validator.checkAgeRange("30", "10");
        assertFalse(result.valid());
    }

    /**
     * Tests age range with non-numeric inputs.
     */
    @Test
    public void nonIntegerAgeRangeTest() {
        WhiskeyDataValidator validator = new WhiskeyDataValidator();
        WhiskeyDataValidator.RangeValidationResponse result = validator.checkAgeRange("abc", "20");
        assertFalse(result.valid());
    }

    /**
     * Tests age range with negative integers.
     */
    @Test
    public void negativeAgeRangeTest() {
        WhiskeyDataValidator validator = new WhiskeyDataValidator();
        WhiskeyDataValidator.RangeValidationResponse result = validator.checkAgeRange("-5", "10");
        assertFalse(result.valid());
    }

    /**
     * Tests age range with empty fields.
     */
    @Test
    public void emptyAgeRangeFieldsTest() {
        WhiskeyDataValidator validator = new WhiskeyDataValidator();
        WhiskeyDataValidator.RangeValidationResponse result = validator.checkAgeRange("", "");
        assertFalse(result.valid());
    }
}
