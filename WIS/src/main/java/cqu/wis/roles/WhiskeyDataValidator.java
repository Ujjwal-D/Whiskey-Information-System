/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

/**
 * Provides input validation for whiskey-related queries.
 */
public class WhiskeyDataValidator {

    /**
     * A record representing a valid age range.
     */
    public record Range(int lower, int upper) {}

    /**
     * A record representing the result of an age range validation.
     */
    public record RangeValidationResponse(boolean valid, Range range, String message) {}

    /**
     * Default constructor.
     */
    public WhiskeyDataValidator() {}

    /**
     * Validates a region string.
     *
     * @param r the region to validate
     * @return ValidationResponse indicating whether it's valid
     */
    public ValidationResponse checkRegion(String r) {
        if (r == null || r.isBlank()) {
            return new ValidationResponse(false, "Region must not be empty.");
        }

        String[] validRegions = {"Islay", "Highland", "Speyside", "Lowland", "Campbeltown"};
        for (String region : validRegions) {
            if (region.equalsIgnoreCase(r.trim())) {
                return new ValidationResponse(true, "Valid region.");
            }
        }

        return new ValidationResponse(false, "Invalid region: " + r);
    }

    /**
     * Validates an age range based on two string inputs.
     *
     * @param b1 lower bound string
     * @param b2 upper bound string
     * @return RangeValidationResponse with validation result
     */
    public RangeValidationResponse checkAgeRange(String b1, String b2) {
        int lower, upper;

        if (b1 == null || b1.isBlank() || b2 == null || b2.isBlank()) {
            return new RangeValidationResponse(false, null, "Both age fields are required.");
        }

        try {
            lower = Integer.parseInt(b1.trim());
            upper = Integer.parseInt(b2.trim());
        } catch (NumberFormatException e) {
            return new RangeValidationResponse(false, null, "Both ages must be valid integers.");
        }

        if (lower < 0 || upper < 0) {
            return new RangeValidationResponse(false, null, "Ages must be non-negative.");
        }

        if (lower > upper) {
            return new RangeValidationResponse(false, null, "Lower age cannot be greater than upper age.");
        }

        return new RangeValidationResponse(true, new Range(lower, upper), "Valid age range.");
    }
}

