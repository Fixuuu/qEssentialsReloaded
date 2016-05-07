package me.kavzaq.qEssentialsReloaded.utils;

import org.bukkit.Bukkit;

public class TPSUtils {
	
	public static double getCurrentTPS() {
		double[] tps = Bukkit.getTPS();
		return formatTPS(tps[0]);
	}
	
	public static double formatTPS(double tps) {
		return Math.min(Math.round(tps * 100.0) / 100.0, 20.0);
	}

}
