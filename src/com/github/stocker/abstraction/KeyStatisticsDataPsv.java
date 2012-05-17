package com.github.stocker.abstraction;

import java.io.IOException;

import au.com.bytecode.opencsv.CSVReader;

/** 
 * Implementation of KeyStatisticsData using PSV data files.
 */
public class KeyStatisticsDataPsv extends KeyStatisticsData {
    
    /** 
     * Constructor with input stock symbol
     * 
     * TODO - This should be a multiton, one and only one instance per symbol
     * 
     * @param  i_stockSymbol Input stock symbol
     */
    public KeyStatisticsDataPsv(String i_stockSymbol) {
        super(i_stockSymbol);
        
        CSVReader l_psvFile = UtilPsv.getCSVReaderKeyStatistics(i_stockSymbol);
        String[] l_line;
        try {
            while ((l_line = l_psvFile.readNext()) != null) {
                
                if(l_line[0].contains("#"))
                    continue;
                
                logger.info("{}  {}",l_line[0],l_line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // TODO - Parse and load the file into our base classes
        //        KeyStatisticsDataInstance
        
        
    }
}
