package com.github.stocker.abstraction;

import java.math.BigDecimal;
import java.util.TreeMap;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
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
        @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
    private String stockSymbol = null;
    
    /** Statistics data for this stock */
    SummaryStatistics stockStats = null;
    
    /** 
     * Default constructor - Not valid so make private
     */
    @SuppressWarnings("unused")
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
     * @param  i_date Date in which data is being requested.  If this parameter
     *                is null then the most recent value is returned.
     */
    public BigDecimal getAdjClosePrice(StockerDate i_date)
    {
        BigDecimal l_num = null;
        if(histData.containsKey(i_date != null ? i_date : histData.lastKey())) {
            l_num = histData.get(i_date).adjClosePrice;
        }            
        return l_num;
    }
    
    /** 
     * Return the stocks trading volume on input date
     * 
     * @param  i_date Date in which data is being requested  If this parameter
     *                is null then the most recent value is returned.
     */
    public int getVolume(StockerDate i_date)
    {
        int l_vol = 0;
        if(histData.containsKey(i_date != null ? i_date : histData.lastKey())) {
            l_vol = histData.get(i_date).volume;
        }            
        return l_vol;
    }
    
    /** 
     * Return the oldest available date for this stock
     * 
     * Note that this function will return null if the data
     * set is empty.
     * 
     */
    public StockerDate getOldestDataDate()
    {
        //logger.debug("OldestData: {}",histData.firstKey().getDateString());
        if(histData.isEmpty()) {
            return null;
        }
        else {
            return histData.firstKey();
        }
    }
    
    /** 
     * Return the newest recording date for this stock
     * 
     * Note that this function will return null if the data
     * set is empty.
     * 
     */
    public StockerDate getNewestDataDate()
    {
        //logger.debug("OldestData: {}",histData.firstKey().getDateString());
        if(histData.isEmpty()) {
            return null;
        }
        else {
            return histData.lastKey();
        }
    }
    
    /** 
     * Return the number of stock prices we have in our historic data
     * 
     */
    public int getNum()
    {        
        return histData.size();
    }
    
    /** 
     * Return the smallest stock price this stock was at during the input
     * date range.
     * 
     * @param i_start Start date to find minimum stock price.  null is 
     *        beginning of time.
     * @param i_stop Stop date to find minimum stock price.  null is 
     *        current date.
     * 
     */
    public BigDecimal getMin(StockerDate i_start, StockerDate i_stop)
    {
        //logger.debug("START DATE:{} END DATE:{}",i_start.getDateString(),i_stop.getDateString());
        if(histData.isEmpty()) {
            return null;
        }
        else {

            SummaryStatistics l_stats = this.getStats(i_start, i_stop);
            //logger.debug("MIN {} MAX {}",l_stats.getMin(), l_stats.getMax());
            return BigDecimal.valueOf(l_stats.getMin());  
        }
    }
    
    /** 
     * Return the largest stock price this stock was at during the input
     * date range.
     * 
     * @param i_start Start date to find maximum stock price.  null is 
     *        beginning of time.
     * @param i_stop Stop date to find maximum stock price.  null is 
     *        current date.
     * 
     */
    public BigDecimal getMax(StockerDate i_start, StockerDate i_stop)
    {
        //logger.debug("START DATE:{} END DATE:{}",i_start.getDateString(),i_stop.getDateString());
        if(histData.isEmpty()) {
            return null;
        }
        else {
            
            SummaryStatistics l_stats = this.getStats(i_start, i_stop);
            //logger.debug("MIN {} MAX {}",l_stats.getMin(), l_stats.getMax());
            return BigDecimal.valueOf(l_stats.getMax());            
        }
    }
    
    /** 
     * Return the average stock price this stock was at during the input
     * date range.
     * 
     * @param i_start Start date to find average stock price.  null is 
     *        beginning of time.
     * @param i_stop Stop date to find average stock price.  null is 
     *        current date.
     * 
     */
    public BigDecimal getMean(StockerDate i_start, StockerDate i_stop)
    {
        if(histData.isEmpty()) {
            return null;
        }
        else {
            
            SummaryStatistics l_stats = this.getStats(i_start, i_stop);
            return BigDecimal.valueOf(l_stats.getMean());            
        }
    }
    
    
    /** 
     * Return the standard deviation for this stock during the input
     * date range.
     * 
     * @param i_start Start date to find maximum stock price.  null is 
     *        beginning of time.
     * @param i_stop Stop date to find maximum stock price.  null is 
     *        current date.
     * 
     */
    public BigDecimal getStdDeviation(StockerDate i_start, StockerDate i_stop)
    {
        if(histData.isEmpty()) {
            return null;
        }
        else {
            
            SummaryStatistics l_stats = this.getStats(i_start, i_stop);
            return BigDecimal.valueOf(l_stats.getStandardDeviation());            
        }
    }
    
    /** 
     * Until we come up with a better way to optimize, we'll provide a
     * reset option so the next date range will be used
     * 
     */
    public void resetStatData()
    {
        stockStats = null;
    }
   
    /** 
     * Internal function to generate and return SummaryStatistics object
     * with the input date range.
     * 
     * @param i_start Start date to find maximum stock price.  null is 
     *        beginning of time.
     * @param i_stop Stop date to find maximum stock price.  null is 
     *        current date.
     * 
     */
    private SummaryStatistics getStats(StockerDate i_start, StockerDate i_stop)
    {   
        StockerDate l_oldest = (i_start != null ? i_start : this.getOldestDataDate());
        StockerDate l_newest = (i_stop != null ? i_stop : this.getNewestDataDate());
        // TODO - Validate the date range is the same as last time we ran
        if(stockStats == null)
        {
            //logger.debug("Start:{} Stop:{}",l_oldest.getDateString(),l_newest.getDateString());
            stockStats = new SummaryStatistics();
            for(StockerDate i= new StockerDate(l_oldest); (i!=null) && (i.compareTo(l_newest) <= 0); i.inc(1)) {
    
                // TODO - Ignore invalid dates (need .inc() to not return invalid dates)
                BigDecimal l_tmp = this.getAdjClosePrice(i);
                if(l_tmp == null)
                    continue;
                
                stockStats.addValue(l_tmp.doubleValue());
            }
        }
        
        return stockStats;
    }
     
}
