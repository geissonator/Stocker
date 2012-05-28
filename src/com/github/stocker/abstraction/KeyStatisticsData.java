package com.github.stocker.abstraction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Base class which represents an input stocks Key Statistics information
 */
public abstract class KeyStatisticsData {
    
    /** Log object */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** Date that the objects data is valid for */
    private String stockSymbol = null;
    
    /** 
     * Default constructor - Not valid so make private
     */
    private KeyStatisticsData() {
        
    }
    
    
    /** 
     * Constructor with input stock symbol
     * 
     * TODO - This should be a multiton, one and only one instance per symbol
     * 
     * @param  i_stockSymbol Input stock symbol
     */
    protected KeyStatisticsData(String i_stockSymbol) {
        stockSymbol = i_stockSymbol;
    }
     
}
