package com.github.stocker.abstraction;

import java.util.Date;

/**
 * Base class which represents an input stocks Historical information on 
 * a specific day.
 */
public abstract class HistoricData {
    
    /** Date that the objects data is valid for */
    Date date = null;
    
    /** Closing price for stock on date */
    float closePrice = 0;
    
    /** 
     * Default constructor - Not valid so make private
     */
    private HistoricData() {
        
    }
    
    /** 
     * Constructor with input date
     * 
     * @param  i_date Input date "YYYY-MM-DD"
     */
    public HistoricData(String i_date) {
        
    }
}
