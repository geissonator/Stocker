package com.github.stocker.abstraction;

/** 
 * Implementation of HistoricData using PSV data files.
 */
public class HistoricDataPsv extends HistoricData{

    /** 
     * Constructor with input stock symbol
     * 
     * TODO - This should be a multiton, one and only one instance per symbol
     * 
     * @param  i_stockSymbol Input stock symbol
     */
    public HistoricDataPsv(String i_stockSymbol) {
        super(i_stockSymbol);
        
        // TODO - Open resources/stock_data/psv and find 
        //        input stock symbol historic file and open
        
        // TODO - Parse and load the file into our base classes
        //        HistoricDataInstance array
        
        this.addStockInstance("date", 0);
        
    }

}
