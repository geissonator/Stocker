package com.github.stocker.abstraction;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.stocker.cmdline.MainCmdLine;

import au.com.bytecode.opencsv.CSVReader;

/** 
 * Implementation of HistoricData using PSV data files.
 */
public class HistoricDataPsv extends HistoricData{

    /** Log object */
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** 
     * Constructor with input stock symbol
     * 
     * TODO - This should be a multiton, one and only one instance per symbol
     * 
     * @param  i_stockSymbol Input stock symbol
     */
    public HistoricDataPsv(String i_stockSymbol) {
        super(i_stockSymbol);
        
        CSVReader l_psvFile = UtilPsv.getCSVReaderHistoric(i_stockSymbol);
        String[] l_line;
        try {
            while ((l_line = l_psvFile.readNext()) != null) {
                
                if(l_line[0].contains("#"))
                    continue;
                
                //logger.info("date:{}  adj close price:{}",l_line[0],l_line[6]);

                BigDecimal l_adjPrice = new BigDecimal(l_line[6]);
                l_adjPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
                this.addStockInstance(l_line[0],l_adjPrice,
                        Integer.parseInt(l_line[5]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
               
    }

}
