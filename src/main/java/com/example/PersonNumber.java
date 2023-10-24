package com.example;

public class PersonNumber extends CitizenNumber {

    public PersonNumber(String id) {
        super(id);
    }

    @Override
    protected String DayCheck(String day) {
        //Logic for checking the day Personnummer
        return day;
    }
    
}