package me.kavzaq.qEssentialsReloaded.runnables.tpsmonitor;

import me.kavzaq.qEssentialsReloaded.utils.Util;

public class TPSMonitor implements Runnable {
	
	public static int tps = 0;
	public static long[] ticks = new long[600];

	@Override
	public void run() {
		ticks[(tps % ticks.length)] = System.currentTimeMillis();
		tps += 1;
	}
	
	public static double getPercentage() {
		double tps = getCurrentTPS();
		double percentage = Double.valueOf(Util.round(100 - (tps * 5), 2));
		return percentage;
	}
	
	public static double getCurrentTPS() {
		if (tps < 100) return -1;
		int elapsedTime = (int) (System.currentTimeMillis() - 
				ticks[(tps - 1 - 100) % ticks.length]);
		
		return Util.round(100 / (elapsedTime / 1000.0), 2);
	}

}
