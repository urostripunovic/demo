package com.example;

import java.util.logging.*;

public class Log {
    public static final Logger logger = Logger.getLogger(Log.class.getSimpleName());


    public static void init() {
        try {
            logger.setUseParentHandlers(false); //Less verbose
            logger.setLevel(Level.ALL); //Adss lower level logs like fine and finer
            FileHandler fh = new FileHandler("logs/FailedChecksLogger.log");
            fh.setFormatter(new SimpleFormatter());           

            logger.addHandler(fh);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception", e);

        }
    }
}
