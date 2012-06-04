package com.github.stocker.plugin;

public class ExamplePlugin implements StockerInterface {

	public StockerPluginResult run() {
		// TODO Auto-generated method stub
		StockerPluginResult x = new StockerPluginResult();
		x.rating.put("IBM", 44.5);
		x.rating.put("AMD", 17.5);
		x.rating.put("INTC", 21.5);
		return x;
	}

}
