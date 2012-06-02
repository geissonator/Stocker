package com.github.stocker.cmdline;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.stocker.abstraction.HistoricData;
import com.github.stocker.abstraction.KeyStatisticsData;
import com.github.stocker.abstraction.StockerDate;
import com.github.stocker.abstraction.StockerFacade;
import com.github.stocker.plugin.ExamplePlugin;
import com.github.stocker.plugin.StockerPluginResult;

public class MainCmdLine {
    
    public static void main(String[] args) {       
        
        // Examples on different type of logging available
        Logger logger = LoggerFactory.getLogger(MainCmdLine.class);
        logger.debug("Debug - Hello World");
        logger.info("Info - Hello World");
        logger.warn("Warn - Hello World");
        logger.error("Error - Hello World");
        logger.debug("Debug - {} {} with Parameters", "Hello","World");
        logger.debug("How to trace more then 2 parameters {} {} {}", new Object[]{1,2,3});
        
        ExamplePlugin p = new ExamplePlugin();
        StockerPluginResult r = p.run();
        
        logger.info (" does result contain ibm: " + r.rating.get("IBM") );
        
        // Load the facade and get all supported stock symbols
        StockerFacade l_facade = new StockerFacade();   
        ArrayList<String> l_stockSymbols = l_facade.getAllSymbols();
        
        // Get the historic data  and key statistics for all available symbols
        for(String l_symbol: l_stockSymbols) {
            logger.info("Stock Symbol: {}",l_symbol);
            HistoricData l_histData = l_facade.getHistoricData(l_symbol);
            KeyStatisticsData l_keyStats = l_facade.getKeyStatisticsData(l_symbol);
            
            StockerDate l_oldest = l_histData.getOldestDataDate();
            StockerDate l_newest = l_histData.getNewestDataDate();
            logger.info("Oldest date for {} is {} at price {} and the newest date is {} at price {}",
                    new Object[]{l_symbol,l_oldest.getDateString(),l_histData.getAdjClosePrice(l_oldest).floatValue(),
                    l_newest.getDateString(),l_histData.getAdjClosePrice(l_newest).floatValue()});
            
            if(l_histData.getAdjClosePrice(l_oldest).intValue() != 0) {
                BigDecimal l_return = l_histData.getAdjClosePrice(l_newest).subtract(l_histData.getAdjClosePrice(l_oldest));
                l_return = l_return.divide(l_histData.getAdjClosePrice(l_oldest), BigDecimal.ROUND_HALF_DOWN);
                logger.info("The rate of return on {} stock from it's inception to now is {}%",
                        l_symbol,l_return.floatValue()*100);
            }
            else
            {
                logger.error("Stock symbol {} has a stock price of 0!",l_symbol);
            }
            /* Comment out for now for performance
             * 
            // Run a simple algorithm to find the lowest and highest stock
            // price for the stock symbol
            BigDecimal l_highest = l_histData.getAdjClosePrice(l_oldest);
            BigDecimal l_lowest = l_histData.getAdjClosePrice(l_oldest);
                        
            for(StockerDate i=l_oldest; (i.compareTo(l_newest) <= 0); i.inc()) {

                // TODO - Ignore invalid dates (need .inc() to not return invalid dates)
                BigDecimal l_tmp = l_histData.getAdjClosePrice(i);
                if(l_tmp == null)
                    continue;
                
                if( l_histData.getAdjClosePrice(i).compareTo(l_highest) > 0) {
                    l_highest = l_histData.getAdjClosePrice(i);                    
                }
                if( l_histData.getAdjClosePrice(i).compareTo(l_lowest) < 0) {
                    l_lowest = l_histData.getAdjClosePrice(i);                    
                }
            }
            
            logger.info("The lowest value for {} stock was {} and the highest was {}",
                    new Object[]{l_symbol,l_lowest,l_highest});
            */
        }        
    }
}
