package com.github.stocker.plugin;

import com.github.stocker.abstraction.StockerDate;

public interface StockerInterface {

    /** The starting rank for a stock (bad) */
    static final int DEFAULT_RANKING_BAD = 1000;
    
    /** The best possible ranking for a stock */
    static final int BEST_POSSIBLE_RANKING = 1;
    
    /** 
     * Main entry point into plugin to execute
     * 
     * @param  i_start Date to start analysis on (when relevant).
     *                 null indicates to run against all time.
     * @param  i_stop  Date to stop analysis on (when relevant).
     *                 null indicates to run up to present time.
     */
    public StockerPluginResult run(StockerDate i_start, StockerDate i_stop);

}
