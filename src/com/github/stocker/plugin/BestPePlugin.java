package com.github.stocker.plugin;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.stocker.abstraction.KeyStatisticsData;
import com.github.stocker.abstraction.StockerDate;
import com.github.stocker.abstraction.StockerFacade;
import com.github.stocker.cmdline.MainCmdLine;

/**
 * Price per Earnings Plugin
 * 
 * This plugin looks at the current PE of all stocks and
 * simply ranks them based on it (best rating goes to lowest PE)
 * 
 * DATA REQUIRED
 * - Trailing PE
 * 
 * 
 */
public class BestPePlugin implements StockerInterface {

    Logger logger = LoggerFactory.getLogger(MainCmdLine.class);
    
    public StockerPluginResult run(StockerDate i_start, StockerDate i_stop) {
        
        StockerPluginResult l_rslt = new StockerPluginResult();
        
        StockerFacade l_facade = new StockerFacade();   
        ArrayList<String> l_stockSymbols = l_facade.getAllSymbols();
        
        // Now cycle through all symbols setting it's ranking equal to it's
        // p/e value.
        for(String l_symbol: l_stockSymbols) {
            KeyStatisticsData l_keyStats = l_facade.getKeyStatisticsData(l_symbol);
            
            Double l_ranking = l_keyStats.getTrailingPE().doubleValue();
            if((l_ranking == 0) || (l_ranking > 100))
            {
                //logger.debug("Stock Symbol: {} has a terrible P/E of {}",l_symbol,l_ranking);
                l_ranking = 100.00;                
            }
                               
            l_rslt.add(l_symbol,l_ranking);
        }
        
        return l_rslt;
    }

}
