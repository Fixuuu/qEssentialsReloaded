package me.kavzaq.qEssentialsReloaded.impl;

import org.bukkit.Bukkit;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class UpdaterImpl {

	private static boolean actualVersion = true;
	private static String newestVersion = "1.0.0R";
	private static String currentVersion = 
			Main.getInstance().getDescription().getVersion();
	
	public static boolean isUpdated() {
		return actualVersion;
	}
	
	public static String getNewestVersion() {
		return newestVersion;
	}
	
	public static String getCurrentVersion() {
		return currentVersion;
	}

	public static void checkUpdate() {
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				try {
					newestVersion = Util.readUrl("http://kavz.za.pl/plugins/qessentials/update.txt");
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
				if (!currentVersion.equalsIgnoreCase(newestVersion)) {
					actualVersion = false;
				}
				
			}
		});
	}

}
