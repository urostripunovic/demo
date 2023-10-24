package com.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.LocalDate;

//Template method
public abstract class CitizenNumber extends Number {

    private String dayFormat;
    private String monthFormat;
    private String yearFormat;

    public CitizenNumber(String id) {
        super(id);
        this.dayFormat = "";
        this.monthFormat = "";
        this.yearFormat = "";
    }

    // Return a day represented as a string
    protected abstract String DayCheck(String day);

    //Check if century is either "18" || "19" || "20"
    @Override
    protected boolean centuryCheck(String century) {
        return century.equals("18") || century.equals("19") || century.equals("20");
    }

    //Check if date is valid input. Month, day as well as leap year dates
    @Override
    protected boolean dateCheck() {
        if (!patternMatches()) return false;
        // Logic for checking the year and month, then days is handled else where
        // look for leap years as well
        yearFormat = matcher.group(3);
        monthFormat = matcher.group(4);
        String day = matcher.group(5);
        dayFormat = DayCheck(day);

        try {
            DateFormat df = new SimpleDateFormat("yyMMdd");
            df.setLenient(false);
            String date = yearFormat + monthFormat + dayFormat;
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Checks if the person is above the age 100 from format and Delimiter
    protected boolean isAboveAge100() {
        //simple checks with both regex and date
        if (!patternMatches() || !dateCheck()) return false;

        //Regex pattern matching
        String century = matcher.group(2) != null ? matcher.group(2) : "";
        String delimiter = matcher.group(6);
        String yy = yearFormat;
        String mm = monthFormat;
        String dd = dayFormat; //already checked in DateCheck, valid day

        //yymmdd format för LocalTime comparison
        int year = century.equals("") ? Integer.parseInt(yy) : Integer.parseInt(century + yy);
        int month = Integer.parseInt(mm);
        int day = Integer.parseInt(dd);
        
        //Today date as well as current year
        LocalDate today = LocalDate.now();
        String yearToday = String.valueOf(today.getYear()).substring(2);
        
        //Above the age 100, requirement said + is above 100 and so is someone born in 1900s
        if (century.equals("18") || delimiter.equals("+")) {
            //System.out.println("Person is above the age of 100");
            return true;
        }
        
        /*
         * A check to see if input year is less than or greater than current year and adds to correct century
         * if the delimiter is either "" or "-" then either "20" or "19" century is added
         * example1: 18 < 23 that means someone is born 2018
         * example2: 24 > 23 that means someone is born 1924
         */
        int adjustedYear = year;
        if (century.equals("")) {
            if (year <= Integer.parseInt(yearToday) && !delimiter.equals("+")) {
                adjustedYear = Integer.parseInt("20" + year);
            } else {
                adjustedYear = Integer.parseInt("19" + year);
            }
        }
        
        //calc age
        LocalDate birthDate = LocalDate.of(adjustedYear, month, day);
        int age = Period.between(birthDate, today).getYears();

        return age >= 100;
    }
}
