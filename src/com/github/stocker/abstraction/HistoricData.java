package com.github.stocker.abstraction;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Base class which represents an input stocks Historical information
 */
public abstract class HistoricData {
    
    /**
     * Base class which represents an input stocks Historical information on 
     * a specific day.
     */
    public class HistoricDataInstance {
        
        /** Closing price for stock on date */
        private BigDecimal adjClosePrice;
        
        /** Stock trading volume on date */
        private int volume = 0;
        
        /** 
         * Default constructor - Not valid so make private
         */
        private HistoricDataInstance() {
            
        }
        
        /** 
         * Constructor with input date
         * 
         * @param  i_closePrice Adjust closing stock price
         * @param  i_volume Trading volume
         */
        public HistoricDataInstance(BigDecimal i_closePrice,int i_volume) {
            adjClosePrice = i_closePrice;
            volume = i_volume;
            //logger.debug("TEST {} {}",adjClosePrice,volume);
        }
    }
    
    /** Log object */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** 
     * Map of historical data with a date,data key pair.
     * Use TreeMap so that the data is sorted and we can simply take the first and
     * last object to get newest and oldest entry in the data.
     */
    //private ArrayList<HistoricDataInstance> histData = new ArrayList<HistoricDataInstance>();
    private TreeMap<StockerDate, HistoricDataInstance> histData = new TreeMap<StockerDate, HistoricDataInstance>();
    
    /** Stock symbol that the objects data is valid for */
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
     * @param  i_date Date in which data was recorded
     * @param  i_closePrice Adjust closing price of stock on input date
     * @param  i_volume Volume of stock traded on input date
     */
    protected void addStockInstance(String i_date, BigDecimal i_closePrice,int i_volume) {
        HistoricDataInstance l_data = new HistoricDataInstance(i_closePrice,i_volume);
        StockerDate l_date = new StockerDate(i_date);
        histData.put(l_date, l_data);
    }
    
    /** 
     * Return the stocks adjust closing price on input date
     * 
     * @param  i_date Date in which data is being requested
     */
    public BigDecimal getAdjClosePrice(StockerDate i_date)
    {
        BigDecimal l_num = null;
        if(histData.containsKey(i_date)) {
            l_num = histData.get(i_date).adjClosePrice;
        }            
        return l_num;
    }
    
    /** 
     * Return the stocks trading volume on input date
     * 
     * @param  i_date Date in which data is being requested
     */
    public int getVolume(StockerDate i_date)
    {
        int l_vol = 0;
        if(histData.containsKey(i_date)) {
            l_vol = histData.get(i_date).volume;
        }            
        return l_vol;
    }
    
    /** 
     * Return the oldest available date for this stock
     */
    public StockerDate getOldestDataDate()
    {
        //logger.debug("OldestData: {}",histData.firstKey().getDateString());
        return histData.firstKey();
    }
    
    /** 
     * Return the newest recording date for this stock
     */
    public StockerDate getNewestDataDate()
    {
        //logger.debug("NewestData: {}",histData.lastKey().getDateString());
        return histData.lastKey();
    }
     
}
