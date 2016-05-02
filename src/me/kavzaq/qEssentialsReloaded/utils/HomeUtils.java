package me.kavzaq.qEssentialsReloaded.utils;

import org.bukkit.entity.Player;

import com.mysql.jdbc.StringUtils;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;

public class HomeUtils {
	
	private static final StringBuilder localsb = new StringBuilder();
	
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
			} else {
				break;
			}
		}
		return localsb.toString();
	}

}
