package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ValidationLoggerCoordinationTest {
    @Test
    public void validCoordinationNumber() {
        var cnbr = new ValidationLogger(new CoordinationNumber("190910799824"));
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberDelimiter() {
        var cnbr = new ValidationLogger(new CoordinationNumber("19091079-9824"));
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberNot100years() {
        var cnbr = new ValidationLogger(new CoordinationNumber("091079-9824"));
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberIs100years() {
        var cnbr = new ValidationLogger(new CoordinationNumber("091079+9824"));
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberIsFrom1800() {
        var cnbr = new ValidationLogger(new CoordinationNumber("18091079+9824"));
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberDelimiterNoCentury() {
        var cnbr = new ValidationLogger(new CoordinationNumber("091079-9824"));
        assertTrue(cnbr.validate());
    }

    @Test
    public void invalidCoordinationNumberLuhns() {
        var cnbr = new ValidationLogger(new CoordinationNumber("190910799823"));
        assertFalse(cnbr.validate());
    }

    @Test
    public void invalidCoordinationNumberDateAbove91() {
        var cnbr = new ValidationLogger(new CoordinationNumber("190910929828"));
        assertFalse(cnbr.validate());
    }

    @Test
    public void invalidCoordinationNumberDateBelow61() {
        var cnbr = new ValidationLogger(new CoordinationNumber("190910609824"));
        assertFalse(cnbr.validate());
    }
}
