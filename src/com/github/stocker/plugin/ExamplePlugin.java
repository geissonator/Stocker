package com.github.stocker.plugin;

/**
 * Example plugin for people to use when creating a new plugin
 * 
 */
public class ExamplePlugin implements StockerInterface {

	public StockerPluginResult run() {
		StockerPluginResult x = new StockerPluginResult();
		x.add("IBM", 44.5);
		x.add("AMD", 17.5);
		x.add("INTC", 21.5);
		return x;
	}

}
