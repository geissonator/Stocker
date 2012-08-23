package com.github.stocker.abstraction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Representation of a date in the Stocker Project
 * 
 * Primary use case for this is as an input to the plugins to provide a 
 * data range and to be used to communicate a date to the HistoricData class.  
 * 
 * Abstracting the data into a class will allow us to optimize the lookup 
 * of the data and provide some nice utility functions to move the dates
 * around based on when the market was open (i.e. skip weekends and such)
 *
 * 
 */
public class StockerDate implements Comparable<StockerDate> {

    /** Log object */
    @SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /** Internal date object */
    private Date iv_date;
    
    /** 
     * Default constructor - Not valid so make private
     */
    @SuppressWarnings("unused")
    private StockerDate() {
        
    }
    
    /** 
     * Constructor which takes a string date in as input
     *
     * Format: YYYY-MM-DD
     */
    public StockerDate (String i_date) {
        
        try {
            iv_date =  new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(i_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //logger.debug("Input Date: {}",iv_date);
    }
    
    /** 
     * Constructor which takes another StockerDate as input
     */
    public StockerDate (StockerDate i_date) {
        
        try {
            iv_date =  new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(i_date.getDateString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //logger.debug("Input Date: {}",iv_date);
    }
    
    /** 
     * Increment current date to next valid stock date
     *
     * @param i_incr Days to increment by.
     *
     * TODO - Ensure it's a valid stock day
     */
    public void inc(int i_incr)
    {
        Calendar l_cal = Calendar.getInstance();
        l_cal.setTime(iv_date);
        l_cal.add(Calendar.DATE, i_incr);
        iv_date = l_cal.getTime();
    }
    
    /** 
     * Decrement current date to next valid stock date
     *
     * @param i_decr Days to decrement by.
     * 
     * TODO - Ensure it's a valid stock day
     */
    public void dec(int i_decr)
    {
        Calendar l_cal = Calendar.getInstance();
        l_cal.setTime(iv_date);
        l_cal.add(Calendar.DATE, (i_decr*-1));
        iv_date = l_cal.getTime();
    }
    
    /** 
     * Return date in expected string format
     *
     * YYYY-MM-DD
     */
    public String getDateString() {
        SimpleDateFormat l_format = new SimpleDateFormat("yyyy-MM-dd");
        String l_date = l_format.format(iv_date);
        return l_date;
    }
    
    /** 
     * Since this class is a key in a hashmap, need to provide equals interface
     */
    public boolean equals(Object i_obj) {
        if(this == i_obj) {
            //logger.debug("Same object!");
            return true;
        }
         if(!(i_obj instanceof StockerDate)) {
             //logger.debug("Not right instance type");
             return false;
         }
         StockerDate l_objData = (StockerDate)i_obj;
         if(this.iv_date.equals(l_objData.iv_date)) {
             //logger.debug("TEST: {} is equal to {}!",this.iv_date.getTime(),l_objData.iv_date.getTime());
             return true;
         }
         else {
             //logger.debug("TEST: {} is NOT equal to {}!",this.iv_date.getTime(),l_objData.iv_date.getTime());
             return false;
         }
    }
    
    /** 
     * Since this class is a key in a hashmap, need to provide hashCode interface
     */
    public int hashCode() {
        //logger.debug("hashCode:{}",iv_date.hashCode());
        return this.iv_date.hashCode();
    }

    /** 
     * Allows easy comparison operations to let us sort StockerDates
     */
    public int compareTo(StockerDate i_cmp) {
        return this.iv_date.compareTo(i_cmp.iv_date);
    }
    
}
