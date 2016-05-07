package me.kavzaq.qEssentialsReloaded.runnables.tpsmonitor;

import org.bukkit.Bukkit;

import me.kavzaq.qEssentialsReloaded.enums.AverageTime;
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
		return Double.valueOf(Util.round(100 - (getCurrentTPS() * 5), 2));
	}
	
	public static double getPercentage(double tps) {
		return Double.valueOf(Util.round(100 - (tps * 5), 2));
	}
	 
	public static double getCurrentTPS() {
		if (tps < 100) return -1;
		int elapsedTime = (int) (System.currentTimeMillis() - 
				ticks[(tps - 1 - 100) % ticks.length]);
		
		return Util.round(100 / (elapsedTime / 1000.0), 3);
	}
	
	public static double getAverageTPS(AverageTime time) {
		double[] tps = Bukkit.getTPS();
		switch (time) {
			case ONE_MIN:
				return formatTPS(tps[0]);
			case FIVE_MIN:
				return formatTPS(tps[1]);
			case FIFTEEN_MIN:
				return formatTPS(tps[2]);
		}
		return -1;
	}
	
	public static double formatTPS(double tps) {
		if (tps > 20.0) return -1;
		return Math.min(Util.round(tps * 100, 3) / 100.0, 20.0);
	}
}
