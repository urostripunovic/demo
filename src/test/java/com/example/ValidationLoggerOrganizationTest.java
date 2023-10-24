package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ValidationLoggerOrganizationTest {
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
        var onbr = new ValidationLogger(new OrganizationNumber(orgNbrs[0]));
        assertTrue(onbr.validate());
    }

    @Test
    public void validOrgNbr2() {
        var onbr = new ValidationLogger(new OrganizationNumber(orgNbrs[1]));
        assertTrue(onbr.validate());
    }

    @Test
    public void validOrgNbr3() {
        var onbr = new ValidationLogger(new OrganizationNumber(orgNbrs[2]));
        assertTrue(onbr.validate());
    }

    @Test
    public void validOrgNbr4() {
        var onbr = new ValidationLogger(new OrganizationNumber(orgNbrs[3]));
        assertTrue(onbr.validate());
    }

    @Test
    public void invalidOrgNbrPrefix() {
        var onbr = new ValidationLogger(new OrganizationNumber("12556601-6399"));
        assertFalse(onbr.validate());
    }

    @Test
    public void invalidOrgNbrLessThan20Middle() {
        var onbr = new ValidationLogger(new OrganizationNumber("12519601-6399"));
        assertFalse(onbr.validate());
    }

    @Test
    public void invalidOrgNbrLessExactly20MiddleWrongLuhns() {
        var onbr = new ValidationLogger(new OrganizationNumber("12519601-6399"));
        assertFalse(onbr.validate());
    }

    @Test
    public void invalidOrgNbrLessExactly20MiddleRightLuhns() {
        var onbr = new ValidationLogger(new OrganizationNumber("16519601-6397"));
        assertTrue(onbr.validate());
    }

    @Test
    public void invalidOrgNbrLessExactly20MiddleRightLuhnsNoPrefix() {
        var onbr = new ValidationLogger(new OrganizationNumber("519601-6397"));
        assertTrue(onbr.validate());
    }
}
