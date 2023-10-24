package com.example;

import static org.junit.Assert.*;
import org.junit.*;

public class PersonNumberTest {
    private String[] pnbrs;

    @Before
    public void setUp() {
        //3 above 100 and 4 at least above 100
        pnbrs = new String[] {
                "201701102384",
                "141206-2380",
                "20080903-2386",
                "7101169295",
                "198107249289",
                "19021214-9819",
                "190910199827",
                "191006089807",
                "192109099180",
                "4607137454",
                "194510168885",
                "900118+9811",
                "189102279800",
                "189912299816"
        };
    }

    @Test
    public void testValidPersonNumber1() {
        PersonNumber personNumber = new PersonNumber(pnbrs[0]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber2() {
        PersonNumber personNumber = new PersonNumber(pnbrs[1]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber3() {
        PersonNumber personNumber = new PersonNumber(pnbrs[2]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber4() {
        PersonNumber personNumber = new PersonNumber(pnbrs[3]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber5() {
        PersonNumber personNumber = new PersonNumber(pnbrs[4]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber6() {
        PersonNumber personNumber = new PersonNumber(pnbrs[5]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber7() {
        PersonNumber personNumber = new PersonNumber(pnbrs[6]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber8() {
        PersonNumber personNumber = new PersonNumber(pnbrs[7]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber9() {
        PersonNumber personNumber = new PersonNumber(pnbrs[8]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber10() {
        PersonNumber personNumber = new PersonNumber(pnbrs[9]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber11() {
        PersonNumber personNumber = new PersonNumber(pnbrs[10]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber12() {
        PersonNumber personNumber = new PersonNumber(pnbrs[11]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber13() {
        PersonNumber personNumber = new PersonNumber(pnbrs[12]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testValidPersonNumber14() {
        PersonNumber personNumber = new PersonNumber(pnbrs[13]);
        assertTrue(personNumber.validate());
    }

    @Test
    public void testInvalidPersonNumber1() {
        PersonNumber personNumber = new PersonNumber("201701272394");
        assertFalse(personNumber.validate());
    }

    @Test
    public void testInvalidPersonNumber2() {
        PersonNumber personNumber = new PersonNumber("190302299813");
        assertFalse(personNumber.validate());
    }
}
