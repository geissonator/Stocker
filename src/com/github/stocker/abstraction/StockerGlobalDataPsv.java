/**
 * 
 */
package com.github.stocker.abstraction;

import java.util.ArrayList;

/**
 * Implementation of StockerInfoData class.
 *
 */
public class StockerGlobalDataPsv extends StockerGlobalData {

    /*
     * @see com.github.stocker.abstraction.StockerInfoData#getStockSymbols()
     */
    @Override
    protected ArrayList<String> getStockSymbols() {
       
        ArrayList<String> l_data = new ArrayList<String>();
        // TODO - Put something in for a test now
        l_data.add("IBM");
        return l_data;
    }

}
