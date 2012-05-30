package com.github.stocker.abstraction;


import java.io.FileNotFoundException;
import java.io.FileReader;

import au.com.bytecode.opencsv.CSVReader;

/** 
 * Provide PSV file manipulation helper functions
 */
public abstract class UtilPsv {

    /** 
     * Return CSVReader object for historic data file
     * 
     * @return CSVReader valid object
     */
    static CSVReader getCSVReaderHistoric(String i_stockSymbol) {
        String l_file = "yahoo.data." + i_stockSymbol + ".historical.prices.psv";
        return getCSVReader(l_file);
    }
    
    /** 
     * Return CSVReader object for key statistic data file
     * 
     * @return CSVReader valid object
     */
    static CSVReader getCSVReaderKeyStatistics(String i_stockSymbol) {
        String l_file = "yahoo.data." + i_stockSymbol + ".key_statistics.psv";
        return getCSVReader(l_file);
    }
    
    /** 
     * Return CSVReader object for input file
     * 
     * @return CSVReader valid object
     */
    private static CSVReader getCSVReader(String i_fileName) {
        
        CSVReader l_reader = null;
        try {
            l_reader= new CSVReader(new FileReader("resources/stock_data/psv/"+i_fileName),'|');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return(l_reader);
    }
    
}
