package com.github.stocker.abstraction;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class HistoricDataTest {

    String iv_symbol = "IBM";
    StockerDate iv_date = new StockerDate("1979-06-29");
    
    @Test
    public void testHistoricData() {
        HistoricData l_data = DataFactory.getHistoricData(iv_symbol);    
        if(l_data == null) {
            fail("null returned by constructor for HistoricData");
        }
    }

    @Test
    public void testGetAdjClosePrice() {
        HistoricData l_data = DataFactory.getHistoricData(iv_symbol); 
        
        BigDecimal l_dec = new BigDecimal("8.07");
        l_dec.setScale(2, BigDecimal.ROUND_HALF_UP);
        if(l_data.getAdjClosePrice(iv_date).compareTo(l_dec) != 0) {
            fail("Adjusted price was not equal to expected!");
        }
        
        // Now make sure we get an error back
        l_dec = l_dec.movePointLeft(1);
        if(l_data.getAdjClosePrice(iv_date).compareTo(l_dec) == 0) {
            fail("Adjusted price should not be equal!");
        }
        
    }

    @Test
    public void testGetVolume() {
        HistoricData l_data = DataFactory.getHistoricData(iv_symbol);        
        if(l_data.getVolume(iv_date) != 1240000) {
            fail("Volume was not equal to expected!");
        }
    }

    @Test
    public void testGetOldestDataDate() {
        HistoricData l_data = DataFactory.getHistoricData(iv_symbol);        
        if(l_data.getOldestDataDate() == null) {
            fail("Oldest Historic Data is not what was expected!");
        }
    }

    @Test
    public void testGetNewestDataDate() {
        HistoricData l_data = DataFactory.getHistoricData(iv_symbol);        
        if(l_data.getNewestDataDate() == null) {
            fail("Newest Historic Data is not what was expected!");
        }
    }

}
