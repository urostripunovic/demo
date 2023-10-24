package com.example;

import static com.example.Log.logger;

public class ValidationLogger implements ValidityCheck {
    private Number number;

    public ValidationLogger(Number number) {
        Log.init();
        this.number = number;
        logger.info("Number that is checked: " + this.number.getClass().getSimpleName());
    }

    @Override
    public boolean validate() {
        if (!number.validate()) {
            printErrors();
            logger.severe("Validation Failed");
            return false;
        }

        if (number instanceof CitizenNumber && ((CitizenNumber) number).isAboveAge100()) {
            printAndLogSuccess("The person is above the age 100");
        }

        printAndLogSuccess("Validation succeed");
        return true;

    }

    //Prints and logs all the failed steps
    private void printErrors() {
        System.out.println("Correct the following errors listed: ");

        if (!number.patternMatches()) {
            printAndLogFailedCheck("No match for input please follow the following format"
                    + "\n" + "yyMMdd-BBBC, yyMMdd+BBBC, yyyyMMdd-BBBC or yyyyMMddBBBC");
            return;
        }

        if (!number.formatCheck())
            printAndLogFailedCheck("Invalid century input value or change the length of the first numbers to 6-8");

        if (!number.dateCheck())
            printAndLogFailedCheck("Invalid day or month input");

        if (!number.numberCheck())
            printAndLogFailedCheck("Invalid length, keep the length to 4");

        if (!number.delimiterCheck())
            printAndLogFailedCheck("Invalid delimiter, only + or -");

        if (!number.luhnsCheck())
            printAndLogFailedCheck("Invalid check value at the last position");

        printAndLogFailedCheck();
    }

    //prints the success of a validation
    private void printAndLogSuccess(String s) {
        logger.fine(s);
        System.out.println(s);
    }

    //Overloaded function that prints the end of a validation
    private void printAndLogFailedCheck() {
        System.out.println();
        logger.info("End of program");
    }

    //Prints and logs the failed checks
    private void printAndLogFailedCheck(String s) {
        System.out.println(s);
        logger.warning(s);
    }
}
