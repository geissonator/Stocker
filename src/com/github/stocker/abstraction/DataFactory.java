package com.github.stocker.abstraction;

/**
 * Factory design pattern which returns the correct type of 
 * object for the historic and key statistics classes
 */
public class DataFactory {

    public static HistoricData getHistoricData(String i_stockSymbol) {
        
        // Nice and simple right now since we only support the 
        // psv type.
        return new HistoricDataPsv(i_stockSymbol);
    }
    
    public static KeyStatisticsData getKeyStatisticscData(String i_stockSymbol) {
        
        // Nice and simple right now since we only support the 
        // psv type.
        return new KeyStatisticsDataPsv(i_stockSymbol);
    }
    
}
