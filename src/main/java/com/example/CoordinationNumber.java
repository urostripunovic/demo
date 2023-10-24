package com.example;

public class CoordinationNumber extends CitizenNumber {

    public CoordinationNumber(String id) {
        super(id);
    }
    
    //Logic for checking the day for Coordination Number
    @Override
    protected String DayCheck(String day) {
        //Subtract 60 to get the day
        String normalDay = "" + (Integer.parseInt(day) - 60);
        return normalDay;
    }

}
