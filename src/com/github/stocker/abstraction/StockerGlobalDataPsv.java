/**
 * 
 */
package com.github.stocker.abstraction;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

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
        File l_dir = new File("resources/stock_data/psv/");
        try {
            String[] l_fileExts = {"psv"};

            Collection<File> l_files = FileUtils.listFiles(l_dir, l_fileExts, false);
 
            for (Iterator<File> iterator = l_files.iterator(); iterator.hasNext();) {
                File l_tmpFile = (File) iterator.next();
                String[] l_tokens = l_tmpFile.getName().split("[.]");
                // yahoo.data.<symbol>.*
                //System.out.println(l_tokens[0] + l_tokens[1] + l_tokens[2]);
                if(!l_data.contains(l_tokens[2])) {
                    l_data.add(l_tokens[2]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_data;
    }

}
