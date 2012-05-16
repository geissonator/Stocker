package com.github.stocker.abstraction;

import java.util.ArrayList;
import java.util.Date;

/**
 * Base class which represents an input stocks Historical information
 */
public abstract class HistoricData {
    
    /**
     * Base class which represents an input stocks Historical information on 
     * a specific day.
     */
    public class HistoricDataInstance {
        
        /** Date that the objects data is valid for */
        Date date = null;
        
        /** Closing price for stock on date */
        float closePrice = 0;
        
        /** 
         * Default constructor - Not valid so make private
         */
        private HistoricDataInstance() {
            
        }
        
        /** 
         * Constructor with input date
         * 
         * @param  i_date Input date "YYYY-MM-DD"
         */
        public HistoricDataInstance(String i_date,float i_closePrice) {
            // TODO - Date parsing - date = Date.parse(i_date);
            closePrice = i_closePrice;
        }
    }
    
    /** 
     * Array of historical data for, 1 for each day of valid data.
     * Use ArrayList since synchronization (i.e. Vector) is not required
     * since we'll guarantee the load via the multiton and then it's
     * read only data
     */
    private ArrayList<HistoricDataInstance> histData = null;
    
    /** Date that the objects data is valid for */
    private String stockSymbol = null;
    
    /** 
     * Default constructor - Not valid so make private
     */
    private HistoricData() {
        
    }
    
    /** 
     * Constructor with input stock symbol
     * 
     * TODO - This should be a multiton, one and only one instance per symbol
     * 
     * @param  i_stockSymbol Input stock symbol
     */
    protected HistoricData(String i_stockSymbol) {
        stockSymbol = i_stockSymbol;
    }
    
    /** 
     * Add a stock instance based on date.
     * 
     * @param  i_stockSymbol Input stock symbol
     */
    protected void addStockInstance(String i_date, float i_closePrice) {
        HistoricDataInstance l_data = new HistoricDataInstance(i_date,i_closePrice);
        histData.add(l_data);
    }
     
}
