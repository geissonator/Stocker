package com.github.stocker.abstraction;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Base class which represents an input stocks Key Statistics information
 */
public abstract class KeyStatisticsData {
    
    /** Log object */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** Date that the objects data is valid for */
    private String stockSymbol = null;    
    
    /** Market cap of company */
    private BigInteger marketCap;
    /** Enterprise value of company */
    private BigInteger enterpriseVal;
    /** Trailing P/E Ratio */
    private BigDecimal trailingPE;
    /** Forward P/E Ratio */
    private BigDecimal fwdPE;
    /** P/E Growth Ratio */
    private BigDecimal peg;
    
    /** 
     * Default constructor - Not valid so make private
     */
    @SuppressWarnings("unused")
    private KeyStatisticsData() {
        
    }
    
    
    /** 
     * Constructor with input stock symbol
     * 
     * TODO - This should be a multiton, one and only one instance per symbol
     * 
     * @param  i_stockSymbol Input stock symbol
     */
    protected KeyStatisticsData(String i_stockSymbol) {
        stockSymbol = i_stockSymbol;
    }
    
    /** 
     * Set the market cap for the stock this object represents
     * 
     * @param  i_marketCap Market cap of stock.
     */
    protected void setMarketCap(BigInteger i_marketCap) {
        marketCap = i_marketCap;
    }
    
    /** 
     * Get the market cap for the stock this object represents
     * 
     * @return  Market cap of stock.
     */
    public BigInteger getMargetCap() {
        if(marketCap == null) {
            logger.error("Stock Symbol {} has no Market Cap!",stockSymbol);
            marketCap = new BigInteger("0");
        }
        return marketCap;
    }
    
    /** 
     * Set the enterprise value
     * 
     * @param  i_enterpriseVal Enterprise value of this stock.
     */
    protected void setEnterpriseVal(BigInteger i_enterpriseVal) {
        enterpriseVal = i_enterpriseVal;
    }
    
    /** 
     * Get the enterprise value for this stock
     * 
     * @return  Enterprise value of stock.
     */
    public BigInteger getEnterpriseVal() {
        if(enterpriseVal == null) {
            logger.error("Stock Symbol {} has no Enterprise PE!",stockSymbol);
            enterpriseVal = new BigInteger("0");
        }
        return enterpriseVal;
    }
    
    /** 
     * Set the trailing PE value
     * 
     * @param  i_trailingPE Trailing PE for this stock.
     */
    protected void setTrailingPE(BigDecimal i_trailingPE) {
        trailingPE = i_trailingPE;
    }
    
    /** 
     * Get the trailing PE value for this stock
     * 
     * @return  Trailing PE
     */
    public BigDecimal getTrailingPE() {
        if(trailingPE == null) {
            logger.error("Stock Symbol {} has no Trailing PE!",stockSymbol);
            trailingPE = new BigDecimal("0");
        }
        return trailingPE;
    }
    
     
}
