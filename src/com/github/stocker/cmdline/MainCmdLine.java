package com.github.stocker.cmdline;


import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.stocker.abstraction.HistoricData;
import com.github.stocker.abstraction.KeyStatisticsData;
import com.github.stocker.abstraction.StockerFacade;

public class MainCmdLine {
    
    public static void main(String[] args) {       
        
        // Examples on different type of logging available
        Logger logger = LoggerFactory.getLogger(MainCmdLine.class);
        logger.debug("Debug - Hello World");
        logger.info("Info - Hello World");
        logger.warn("Warn - Hello World");
        logger.error("Error - Hello World");
        logger.debug("Debug - {} {} with Parameters", "Hello","World");
        
        // Load the facade and get all supported stock symbols
        StockerFacade l_facade = new StockerFacade();   
        ArrayList<String> l_stockSymbols = l_facade.getAllSymbols();
        
        // Get the historic data  and key statistics for all available symbols
        for(String l_symbol: l_stockSymbols) {
            logger.info("Stock Symbol: {}",l_symbol);
            HistoricData[] l_histData = l_facade.getHistoricData(l_symbol);
            KeyStatisticsData l_keyStats = l_facade.getKeyStatisticsData(l_symbol);
        }
        
    }
}
