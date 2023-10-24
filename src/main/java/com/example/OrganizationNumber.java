package com.example;

public class OrganizationNumber extends Number {
    
    public OrganizationNumber(String id) {
        super(id);
    }

    @Override
    protected boolean centuryCheck(String century) {
        return century.equals("16");
    }

    @Override
    protected boolean dateCheck() {
        //implementation for checking the middle values are >= 20
        String middle = matcher.group(4);
        return Integer.parseInt(middle) >= 20;
    }
    
}
