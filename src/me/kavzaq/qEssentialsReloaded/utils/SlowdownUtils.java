package me.kavzaq.qEssentialsReloaded.utils;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

public class SlowdownUtils {
	
	private static HashMap<Player, Long> lastMessageSended = Maps.newHashMap();

	public static HashMap<Player, Long> getLastMessages() {
		return lastMessageSended;
	}

	public static void setLastMessage(Player p, long l) {
		lastMessageSended.put(p, l);
	}
	
	public static boolean isBlocked(Player p) {
		long timeSended = lastMessageSended.get(p) == null ? 0 : lastMessageSended.get(p);
		if(timeSended == 0) return false; 
		long timeCurrent = System.currentTimeMillis();
		if(timeSended > timeCurrent) {
			return true;
		}
		return false;
	}
	
	public static String timeRemain(Player p) {
		return Long.toString(((lastMessageSended.get(p) == null ? 0 : lastMessageSended.get(p) - System.currentTimeMillis()) / 1000) + 1);
	}

}
