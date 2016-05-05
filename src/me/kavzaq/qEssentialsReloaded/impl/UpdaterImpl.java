package me.kavzaq.qEssentialsReloaded.impl;

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
	
	// probowalem nowym watkiem to zrobic ale cos sie nie wykonywalo ._.
	public static boolean checkUpdate() {
		try {
			newestVersion = Util.readUrl("http://kavz.za.pl/plugins/qessentials/update.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!currentVersion.equalsIgnoreCase(newestVersion)) {
			actualVersion = false;
		}
		return actualVersion;
	}

}
