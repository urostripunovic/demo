package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Template method
public abstract class Number implements ValidityCheck {
    private final String id;
    private static final String regex = "^((\\d{2})?(\\d{2})(\\d{2})(\\d{2}))([-|+]?)?(((?!000)\\d{3})(\\d))$";
    /*
     * regex for input string
     * Match 1 matches yyMMddBBBC, yyyyMMddBBBC, yyMMdd-BBBC, yyMMdd+BBBC, yyyyMMdd-BBBC, yyyyMMdd+BBBC
     * YYMMDD group 1, century optional group 2, year group 3, month group 4, day group 5
     * delimiter optional group 6, BBBC group 7, BBB is between 001-999 birthnumbers grupp 8
     * and check sum is group 9
     */
    private static final Pattern pattern = Pattern.compile(regex);
    protected static Matcher matcher;

    public Number(String id) {
        this.id = id;
        Number.matcher = pattern.matcher(this.id);
    }

    /*
     * Check the date for:
     * Person number just the day
     * Coordination number subtract the day with 60
     * Organization number check if the middle pair is >= 20
     */
    abstract protected boolean dateCheck();

    // Pnbr&Snbr&Onbr samma kod förutom århundrade 16
    protected abstract boolean centuryCheck(String century);

    /*  
     * Checks the format of a 6-8 string for Personnummer/Samordningsnummer och Organisationsnummer
     * The optional century tag is checked differently
    */
    protected boolean formatCheck() {
        if (!patternMatches()) return false;
        String format = matcher.group(1);
        String century = matcher.group(2);
        // century is optional so making sure it's not null means a check is needed
        boolean centuryCheck = century != null ? centuryCheck(century) : false;

        boolean shortInput = format.length() == 6;
        boolean longInput = format.length() == 8 && centuryCheck;

        return shortInput || longInput;
    }

    // Checks the format of four last numbers
    protected boolean numberCheck() {
        if (!patternMatches()) return false;

        return matcher.group(7).length() == 4;
    }

    // Checks the proper delimiter of the string
    protected boolean delimiterCheck() {
        // if there is no match that means the input doesn't have "" || "+" || "-"
        if (!patternMatches()) return false;
        return true;
    }

    // Checks if the check value is the same as the algo, 
    protected boolean luhnsCheck() {
        if (!patternMatches()) return false;

        //C - check value from the input
        String controlNumber = matcher.group(9);
        //yyMMddBBB - birthday without century and birth numbers
        String num = matcher.group(3) + matcher.group(4) + matcher.group(5) + matcher.group(8);
        int[] digits = convertStringToArray(num);

        int sum = 0;

        for (int i = 0; i < digits.length; i++) {
            int luhnFactor = i % 2 == 0 ? 2 : 1;
            int value = digits[i] * luhnFactor;

            //9*2 is 18 then the sum digits becomes 1 + 8, wiki
            if (value > 9)
                sum += 1 + value % 10;
            else
                sum += value;
        }

        int checkNumber = (10 - (sum % 10)) % 10;

        return checkNumber == Integer.valueOf(controlNumber);
    }

    // Command pattern där execute av valideringen körs
    @Override
    public boolean validate() {
        return formatCheck() && dateCheck() && numberCheck() && delimiterCheck() && luhnsCheck();
    }

    //Helper function for regex, if any of the groups fail then that means the input is invalid
    protected boolean patternMatches() {
        return matcher.matches();
    }

    //Helper functions for turning string input to an int array for Luhns algorithm
    private int[] convertStringToArray(String s) {
        int[] res = new int[s.length()];

        for (int i = 0; i < res.length; i++)
            res[i] = Character.getNumericValue(s.charAt(i));

        return res;
    }
}
