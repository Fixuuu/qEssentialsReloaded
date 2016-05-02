package me.kavzaq.qEssentialsReloaded.utils;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.GameMode;

public class GameModeUtils {
	
	private static GameMode getGameMode(int tryb) {
		switch(tryb) {
			case 1: return GameMode.CREATIVE;
			case 2: return GameMode.ADVENTURE;
			case 3: return GameMode.SPECTATOR;
			case 0: return GameMode.SURVIVAL;
			default: return null;
		}
	}
	
	private static GameMode getGameMode(String name) {
		switch(name) {
			case "c": return GameMode.CREATIVE;
			case "creative": return GameMode.CREATIVE;
			case "surv": return GameMode.SURVIVAL;
			case "s": return GameMode.SURVIVAL;
			case "survival": return GameMode.SURVIVAL;
			case "adv": return GameMode.ADVENTURE;
			case "adventure": return GameMode.ADVENTURE;
			case "a": return GameMode.ADVENTURE;
			case "sp": return GameMode.SPECTATOR;
			case "spectator": return GameMode.SPECTATOR;
			case "spec": return GameMode.SPECTATOR;
		default: return null;
		}
	}
	
	public static GameMode parseGameMode(String name) {
		if(StringUtils.isNumeric(name)) {
			return getGameMode(Integer.parseInt(name));
		} else {
			return getGameMode(name);
		}
	}

}
