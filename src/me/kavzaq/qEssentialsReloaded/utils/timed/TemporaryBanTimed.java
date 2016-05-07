package me.kavzaq.qEssentialsReloaded.utils.timed;

import java.util.HashMap;
import java.util.UUID;

import com.google.common.collect.Maps;

import me.kavzaq.qEssentialsReloaded.utils.Util;

public class TemporaryBanTimed {
	
	private static HashMap<UUID, Long> lastTempBan = Maps.newHashMap();

	public static HashMap<UUID, Long> getLastTempBans() {
		return lastTempBan;
	}

	public static void setLastTempBan(UUID p, long l) {
		lastTempBan.put(p, l);
	}
	
	public static boolean isBlocked(UUID p) {
		long timeSended = lastTempBan.get(p) == null ? 0 : lastTempBan.get(p);
		if(timeSended == 0) return false; 
		long timeCurrent = System.currentTimeMillis();
		if(timeSended > timeCurrent) {
			return true;
		}
		return false;
	}
	
	public static String timeRemain(UUID p) {
		return Util.parseTime(((lastTempBan.get(p) == null ? 0 : lastTempBan.get(p) - System.currentTimeMillis())) + 1);
	}

}
