package com.github.stocker.abstraction;

import static org.junit.Assert.*;

import org.junit.Test;

public class StockerDateTest {

    @Test
    public void testStockerDate() {
        StockerDate l_date = new StockerDate("2000-11-11");
    }

    @Test
    public void testInc() {
        StockerDate l_date = new StockerDate("2000-11-11");
        l_date.inc(1);
        String l_incDate = "2000-11-12";
        if(!l_incDate.equals(l_date.getDateString())) {
            fail("Increment of date failed!");
        }
        
    }

    @Test
    public void testDec() {
        StockerDate l_date = new StockerDate("2000-11-11");
        l_date.dec(1);
        String l_incDate = "2000-11-10";
        if(!l_incDate.equals(l_date.getDateString())) {
            fail("Decrement of date failed!");
        }
    }

}
