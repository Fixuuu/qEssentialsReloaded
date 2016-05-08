package me.kavzaq.qEssentialsReloaded.utils;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.GameMode;

public class GameModeUtils {
	
	private GameModeUtils() { }
	public static GameMode getGameMode(String name) {
		for (GameMode mode : GameMode.values()) {
			if (mode.name().toLowerCase().contains(name.toLowerCase())) {
				return mode;
			}
		}
		return null;
	}
	
	@SuppressWarnings("deprecation")
	public static GameMode parseGameMode(String name) {
		if(StringUtils.isNumeric(name)) {
			return GameMode.getByValue(Integer.valueOf(name));
		} else {
			return getGameMode(name);
		}
	}

}
