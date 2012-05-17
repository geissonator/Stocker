package com.github.stocker.abstraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.stocker.cmdline.MainCmdLine;

/**
 * Facade class into stocker data
 * 
 * Currently it doesn't hide much complexity but some day the information returned
 * by these interfaces will come from a variety of sources.
 * 
 * An alternative implementation is to provide a predicate based solution which
 * allows users to define and implement their own predicates which then are run 
 * over the data.  I'm worried about the efficiency of this though. 
 * 
 * TODO - Implement a factory design pattern to generate the appropriate object
 *        types for HistoricData and KeyStatisticsData based on whether we're
 *        supporting PSV or SQL
 * 
 * TODO - Need to define interfaces and data in HistoricData and KeyStatisticsData
 *        and then implement inherited objects from them that support the PSV
 *        format.  
 * 
 */
public class StockerFacade {

    /** Log object */
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** Contains all stock symbols supported by stocker */
    private static ArrayList<String> stockSymbols = new ArrayList<String>();
    
    
    /** 
     * Default constructor
     * TODO - Should be singleton (i.e. no need for multiple instances)
     */
    public StockerFacade() {
        
    }
    
    /** 
     * Return all stock symbols available in our repository
     * 
     * @return Array of all stock symbols within stocker data repository
     * TODO - Implement interface to return all stock symbols
     */
    public ArrayList<String> getAllSymbols() {
        synchronized(stockSymbols) {
            if(stockSymbols.size() == 0) {
                // Load in all stock symbols
                
                // TODO - Put something in for a test now
                stockSymbols.add("IBM");
            }
        }    
        // TODO - Might want to return a copy of the vector
        //        to avoid abuse by caller
        logger.error("TODO - getAllSymbols() - Implement Me");
        return(stockSymbols);
    }
    
    /** 
     * Return historic data for input stock symbol
     * 
     * @param i_stockSymbol Stock symbol to collect data for
     * 
     * @return HistoricData object for input stock symbol
     * 
     */
    public HistoricData getHistoricData(String i_stockSymbol) {

        HistoricData l_data = DataFactory.getHistoricData(i_stockSymbol);
        return(l_data);
    }
    
    /** 
     * Return array of all available historic data for each input stock symbol
     * 
     * @param i_stockSymbols Array of stock symbols to collect historical
     *                       data for.
     *                       
     * @return Map of stock symbol to array of historical data for that
     *         stock symbol
     * 
     */
    public Map<String, HistoricData> getHistoricData(ArrayList<String> i_stockSymbols) {

        Map<String, HistoricData> l_data = new HashMap<String, HistoricData>();
        for(String l_symbol: i_stockSymbols) {
            l_data.put(l_symbol, getHistoricData(l_symbol));
        }
        
        return(l_data);
    }
    
    /** 
     * Return key statistic data for input stock symbol
     * 
     * @param i_stockSymbol Stock symbol to collect data for
     * 
     * @return KeyStatisticsData object for input stock symbol
     */
    public KeyStatisticsData getKeyStatisticsData(String i_stockSymbol) {

        KeyStatisticsData l_data = DataFactory.getKeyStatisticscData(i_stockSymbol);
        return(l_data);
    }
    
    /** 
     * Return array of all available key statistic data for each input stock symbol
     * 
     * @param i_stockSymbols Array of stock symbols to collect key statistic
     *                       data for.
     *                       
     * @return Map of stock symbol to key statistic data object for that
     *         stock symbol
     */
    public Map<String, KeyStatisticsData> getKeyStatisticsData(ArrayList<String> i_stockSymbols) {

        Map<String, KeyStatisticsData> l_data = new HashMap<String, KeyStatisticsData>();
        for(String l_symbol: i_stockSymbols) {
            l_data.put(l_symbol, getKeyStatisticsData(l_symbol));
        }
        return(l_data);
    }
    
}
