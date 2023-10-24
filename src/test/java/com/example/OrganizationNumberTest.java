package com.example;

import static org.junit.Assert.*;
import org.junit.*;

public class OrganizationNumberTest {
    private String[] orgNbrs;

    @Before
    public void setup() {
        orgNbrs = new String[] {
            "556614-3185",
            "16556601-6399",
            "262000-1111",
            "857202-7566"
        };
    }

    @Test
    public void validOrgNbr1() {
        OrganizationNumber onbr = new OrganizationNumber(orgNbrs[0]);
        assertTrue(onbr.validate());
    }

    @Test
    public void validOrgNbr2() {
        OrganizationNumber onbr = new OrganizationNumber(orgNbrs[1]);
        assertTrue(onbr.validate());
    }

    @Test
    public void validOrgNbr3() {
        OrganizationNumber onbr = new OrganizationNumber(orgNbrs[2]);
        assertTrue(onbr.validate());
    }

    @Test
    public void validOrgNbr4() {
        OrganizationNumber onbr = new OrganizationNumber(orgNbrs[3]);
        assertTrue(onbr.validate());
    }

    @Test
    public void invalidOrgNbrPrefix() {
        OrganizationNumber onbr = new OrganizationNumber("12556601-6399");
        assertFalse(onbr.validate());
    }

    @Test
    public void invalidOrgNbrLessThan20Middle() {
        OrganizationNumber onbr = new OrganizationNumber("12519601-6399");
        assertFalse(onbr.validate());
    }

    @Test
    public void invalidOrgNbrLessExactly20MiddleWrongLuhns() {
        OrganizationNumber onbr = new OrganizationNumber("12519601-6399");
        assertFalse(onbr.validate());
    }

    @Test
    public void invalidOrgNbrLessExactly20MiddleRightLuhns() {
        OrganizationNumber onbr = new OrganizationNumber("16519601-6397");
        assertTrue(onbr.validate());
    }

    @Test
    public void invalidOrgNbrLessExactly20MiddleRightLuhnsNoPrefix() {
        OrganizationNumber onbr = new OrganizationNumber("519601-6397");
        assertTrue(onbr.validate());
    }
}
