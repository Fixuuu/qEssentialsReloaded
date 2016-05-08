package me.kavzaq.qEssentialsReloaded.utils;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.mysql.jdbc.StringUtils;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;

public class ListingUtils {
	
	private static final StringBuilder localsb = new StringBuilder();
	private ListingUtils() { }
	
	public static String getHomeList(Player player) {
		localsb.setLength(0);
		UserImpl user = Main.getUserManager().getUser(player);
		for (String home : user.getHomes()) { 
			if (!StringUtils.isNullOrEmpty(home)) {
				HomeDataImpl _home = SerializeUtils.deserializeHome(home);
				localsb.append(MessagesImpl.WHOIS_HOMES_INDEX
						.replace("%home%", _home.getName())
						.replace("%location%", 
								"x" + _home.getLocation().getX() +
								", y" + _home.getLocation().getY() +
								", z" + _home.getLocation().getZ())
						+ "\n");
			}
		}
		return localsb.toString();
	}
	
	public static String getWorldList() {
		localsb.setLength(0);
		for (World w : Bukkit.getWorlds()) {
			int tiles = 0;
			Chunk[] loadedChunks = w.getLoadedChunks();
			for (Chunk c : loadedChunks) {
				tiles += c.getTileEntities().length;
			}
			localsb.append(MessagesImpl.GARBAGECOLLECTOR_WORLD_FORMAT
						.replace("%world%", w.getName())
						.replace("%objects%", String.valueOf(w.getEntities().size()))
						.replace("%chunks%", String.valueOf(loadedChunks.length))
						.replace("%tiles%", String.valueOf(tiles))
					+ "\n");
		}
		return localsb.toString();
	}

}
