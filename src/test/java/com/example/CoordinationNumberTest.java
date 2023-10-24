package com.example;

import static org.junit.Assert.*;
import org.junit.*;

public class CoordinationNumberTest {
    @Test
    public void validCoordinationNumber() {
        CoordinationNumber cnbr = new CoordinationNumber("190910799824");
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberDelimiter() {
        CoordinationNumber cnbr = new CoordinationNumber("19091079-9824");
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberNot100years() {
        CoordinationNumber cnbr = new CoordinationNumber("091079-9824");
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberIs100years() {
        CoordinationNumber cnbr = new CoordinationNumber("091079+9824");
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberIsFrom1800() {
        CoordinationNumber cnbr = new CoordinationNumber("18091079+9824");
        assertTrue(cnbr.validate());
    }

    @Test
    public void validCoordinationNumberDelimiterNoCentury() {
        CoordinationNumber cnbr = new CoordinationNumber("091079-9824");
        assertTrue(cnbr.validate());
    }

    @Test
    public void invalidCoordinationNumberLuhns() {
        CoordinationNumber cnbr = new CoordinationNumber("190910799823");
        assertFalse(cnbr.validate());
    }

    @Test
    public void invalidCoordinationNumberDateAbove91() {
        CoordinationNumber cnbr = new CoordinationNumber("190910929828");
        assertFalse(cnbr.validate());
    }

    @Test
    public void invalidCoordinationNumberDateBelow61() {
        CoordinationNumber cnbr = new CoordinationNumber("190910609824");
        assertFalse(cnbr.validate());
    }
}
