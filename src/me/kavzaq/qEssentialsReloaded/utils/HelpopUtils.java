package me.kavzaq.qEssentialsReloaded.utils;

import java.util.HashMap;

import org.bukkit.command.CommandSender;

import com.google.common.collect.Maps;

public class HelpopUtils {
	
	private static HashMap<CommandSender, Long> lastHelpopSended = Maps.newHashMap();

	public static HashMap<CommandSender, Long> getLastHelpops() {
		return lastHelpopSended;
	}

	public static void setLastHelpop(CommandSender p, long l) {
		lastHelpopSended.put(p, l);
	}
	
	public static boolean isBlocked(CommandSender s) {
		long timeSended = lastHelpopSended.get(s) == null ? 0 : lastHelpopSended.get(s);
		if(timeSended == 0) return false; 
		long timeCurrent = System.currentTimeMillis();
		if(timeSended > timeCurrent) {
			return true;
		}
		return false;
	}
	
	public static String timeRemain(CommandSender s) {
		return Util.parseTime(((lastHelpopSended.get(s) == null ? 0 : lastHelpopSended.get(s) - System.currentTimeMillis())) + 1);
	}


}
