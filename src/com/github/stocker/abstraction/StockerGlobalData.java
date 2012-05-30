package com.github.stocker.abstraction;

import java.util.ArrayList;

/** 
 * Base class to provide general stock information interfaces
 * 
 * Must be implemented by data specific sub-class
 */
public abstract class StockerGlobalData {
    
    /** 
     * Return all stock symbols with data in our repository
     * 
     * @return Array of stock symbols supported by 
     */
    protected abstract ArrayList<String> getStockSymbols();

}
