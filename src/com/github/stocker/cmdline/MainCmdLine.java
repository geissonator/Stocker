package com.github.stocker.cmdline;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainCmdLine {
    
    public static void main(String[] args) {       
        Logger logger = LoggerFactory.getLogger(MainCmdLine.class);
        logger.debug("Debug - Hello World");
        logger.info("Info - Hello World");
        logger.warn("Warn - Hello World");
        logger.error("Error - Hello World");
        logger.debug("Debug - {} {} with Parameters", "Hello","World");
    }
}
