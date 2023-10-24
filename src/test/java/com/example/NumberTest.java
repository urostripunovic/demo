package com.example;

import static org.junit.Assert.*;
import org.junit.*;

public class NumberTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void testValidNumberCheck() {
        assertTrue(null, new PersonNumber("220101-1234").numberCheck());
    }

    @Test //(expected = IllegalStateException.class)
    public void testInvalidToShortNumberCheck() {
        assertFalse(null, new PersonNumber("220101-123").numberCheck());
    }

    @Test //(expected = IllegalStateException.class)
    public void testInvalidToLongNumberCheck() {
        assertFalse(null, new PersonNumber("220101-12345").numberCheck());
    }

    @Test //(expected = IllegalStateException.class)
    public void testInvalidToWithCharsToLongNumberCheck() {
        assertFalse(null, new PersonNumber("220101-1234a").numberCheck());
    }

    @Test //(expected = IllegalStateException.class)
    public void testInvalidToWithCharsToShortNumberCheck() {
        assertFalse(null, new PersonNumber("220101-13a").numberCheck());
    }
    
    @Test //(expected = IllegalStateException.class)
    public void testInvalidToWithCharsValidLenNumberCheck() {
        assertFalse(null, new PersonNumber("220101-123a").numberCheck());
    }


    @Test
    public void testValidPlusDelimiter() {
        assertTrue(null, new PersonNumber("220101+1234").delimiterCheck());
    }

    @Test
    public void testValidPlusLongerDelimiter() {
        assertTrue(null, new PersonNumber("19220101+1234").delimiterCheck());
    }

    @Test
    public void testValidMinusDelimiter() {
        assertTrue(null, new PersonNumber("220101-1234").delimiterCheck());
    }

    @Test
    public void testNoDelimiter() {
        assertTrue(null, new PersonNumber("2201011234").delimiterCheck());
    }

    @Test //(expected = IllegalStateException.class)
    public void testWrongDelimiter() {
        assertFalse(null, new PersonNumber("220101a1234").delimiterCheck());
    }

    @Test
    public void testValidLuhn() {
        assertTrue(null, new PersonNumber("220101-1232").luhnsCheck());
    }

    @Test
    public void testInvalidLuhn() {
        assertFalse(null, new PersonNumber("220101-1233").luhnsCheck());
    }

    @Test
    public void testInvalidLuhnTestData() {
        assertFalse(null, new PersonNumber("201701272394").luhnsCheck());
    }

    @Test
    public void testValidFormCheck() {
        assertTrue(null, new PersonNumber("20170127-2394").formatCheck());
    }

    @Test
    public void testToBigCenturyFormCheck() {
        assertFalse(null, new PersonNumber("21170127-2394").formatCheck());
    }

    @Test
    public void testToSmallCenturyFormCheck() {
        assertFalse(null, new PersonNumber("17170127-2394").formatCheck());
    }

    @Test //(expected = IllegalStateException.class)
    public void testToShortInputFormCheck() {
        assertFalse(null, new PersonNumber("1170127-2394").formatCheck());
    }

    @Test //(expected = IllegalStateException.class)
    public void testToLongInputFormCheck() {
        assertFalse(null, new PersonNumber("111170127-2394").formatCheck());
    }

    @Test
    public void testValidDateCheck() {
        assertTrue(null, new PersonNumber("20170127-2394").dateCheck());
    }

    @Test
    public void testInvalid13MonthDateCheck() {
        assertFalse(null, new PersonNumber("20171327-2394").dateCheck());
    }

    @Test
    public void testInvalid00MonthDateCheck() {
        assertFalse(null, new PersonNumber("20170027-2394").dateCheck());
    }

    @Test
    public void testInvalidDayOnMonthDateCheck() {
        assertFalse(null, new PersonNumber("20170132-2394").dateCheck());
    }

    @Test
    public void testValidLeapYearDateCheck() {
        assertTrue(null, new PersonNumber("240229-2394").dateCheck());
    }

    @Test
    public void testInvalidLeapYearDateCheck() {
        assertFalse(null, new PersonNumber("210229-2394").dateCheck());
    }

    /*
     * Kolla fel månad 
     * fel dag 
     * fel check siffra
     * fel delimiter
     * för lång/kort form
     * för lång/kort number
     * rätt dagar med eller utan delimiters
     */
}
