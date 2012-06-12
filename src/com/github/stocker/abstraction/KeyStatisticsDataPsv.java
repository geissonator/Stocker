package com.github.stocker.abstraction;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

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
                
                if(l_line[0].equalsIgnoreCase("Market Cap (intraday)")) {
                    this.setMarketCap(getBigInteger(l_line[1]));
                }
                else if(l_line[0].equalsIgnoreCase("Enterprise Value")) {
                    this.setEnterpriseVal(getBigInteger(l_line[1]));
                }
                else if(l_line[0].equalsIgnoreCase("Trailing P/E (ttm, intraday)")) {
                    this.setTrailingPE(getBigDecimal(l_line[1]));
                }
                //logger.info("{}  {}",l_line[0],l_line[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // TODO - Parse and load the file into our base classes
        //        KeyStatisticsDataInstance
        
        
    }
    
    private static BigDecimal getBigDecimal(String i_bd) {
        try {
            BigDecimal l_bd = new BigDecimal(i_bd);
            l_bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            return l_bd;
        }
        catch (NumberFormatException exc) {
            return (new BigDecimal("0"));
        }
    }
    
    private static BigInteger getBigInteger(String i_bi) {
        try {
            BigInteger l_bi = new BigInteger(i_bi);
            return l_bi;
        }
        catch (NumberFormatException exc) {
            return (new BigInteger("0"));
        }
    }
}
