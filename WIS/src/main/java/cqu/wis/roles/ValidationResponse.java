/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cqu.wis.roles;

/**
 * A record representing the result of a validation operation.
 * 
 * @param valid whether the input was valid
 * @param message validation error or success message
 */
public record ValidationResponse(boolean valid, String message) { }

