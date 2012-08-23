package com.github.stocker.plugin;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Keep track of plugin results and display data
 */
public class StockerPluginResult {

    private HashMap<String, Double> map = new HashMap<String, Double>();
    private ValueComparator bvc = new ValueComparator(map);
    private TreeMap<String, Double> rating = new TreeMap<String, Double>(bvc);

    /** 
     * Add a stock and it's rating to the result object
     * 
     * @param i_stock Stock symbol to set rating for
     * @param i_rating Rating for input stock symbol
     *
     */
    public void add(String i_stock, Double i_rating) {
        map.put(i_stock, i_rating != null ? i_rating : 0);
    }

    /** 
     * Get the rating for the input stock symbol
     * 
     * @param i_stock Stock symbol to get rating for
     *
     * @return Stock rating value
     */
    public Double get(String i_stock) {
        return (map.get(i_stock));
    }

    /** 
     * Print top i_count stock symbols and their rating
     * 
     * @param i_count Number of stock symbols to display
     *
     */
    public void print(int i_count) {
        
        rating.putAll(map);
        
        // TODO - Should be rating.get but we get null for certain value so just
        //        use the map.  It's sorted correctly so it all works
        for (String key : rating.keySet()) {
            System.out.println("key/value: " + key + "/" + map.get(key));
            if(i_count-- == 0) {
                break;
            }
        }
    }
    
    /** 
     * Combine the results of this object with those of another.
     * 
     * @param i_data Data to combine with this object
     *
     */
    public void combine(StockerPluginResult i_data)
    {
        // TODO
        
    }
}

class ValueComparator implements Comparator<Object> {

    Map<String, Double> base;

    public ValueComparator(Map<String, Double> base) {
        this.base = base;
    }

    public int compare(Object a, Object b) {
        // Sort in ascending order
        if ((Double) base.get(a) < (Double) base.get(b)) {
            return -1;
        } else if ((Double) base.get(a) == (Double) base.get(b)) {
            return 0;
        } else {
            return 1;
        }
    }
}
