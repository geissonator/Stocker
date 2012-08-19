package com.github.stocker.sqlite.sample;

import java.io.File;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteConstants;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

/*
 * The purpose of this sample is it demonstrate connecting to the yStocker.db
 * SQLITE database, running an SQL query, and accessing the data returned. It 
 * is intended to serve as a starting point for further development.
 *  
 * Further references for this sample can be found at the following link:
 * http://code.google.com/p/sqlite4java/ 
 * 
 */

public class SqliteSample {
	
	public static void main(String[] args) throws SQLiteException {  
		
		String dbFile="/var/tmp/stocker/data/yStocker.db";

		SQLiteConnection db = new SQLiteConnection(new File(dbFile));
		try {
			db.open(true);
		} catch (Exception e) {
			System.out.print("Exception occurred: " + e);
		}

		// A simple query to return key stats for stock symbol IBM 
		SQLiteStatement st = db.prepare("SELECT * FROM ystat_view WHERE name = \'IBM\' ");

		try {
			// you can get column count and names before or after st.step()
			for (int i = 0; i < st.columnCount(); i++) {
				String columnName = st.getColumnName(i);
				System.out.println("Col name i: " + i + " is: " + columnName);
			}

			while (st.step()) {
				
				// type can be checked only after st.step() for the current row and if no type conversions took place
				for (int i = 0; i < st.columnCount(); i++) {
					int type = st.columnType(0);
					if (type == SQLiteConstants.SQLITE_INTEGER) {
						System.out.println("Col value with SQLITE_INTEGER is : " + st.columnInt(i));
					} else if (type == SQLiteConstants.SQLITE_TEXT) {
						System.out.println("Col value with SQLITE_TEXT is : " + st.columnString(i));
					} else {
						System.out.println("Col value - unhandled type is: " + st.columnValue(i).toString());        
					}
				}
			}

		} catch (Exception e) {
			System.out.print("Exception occurred: " + e);
		} finally {
			st.dispose();
		}

		db.dispose();
	}   
	    
}
