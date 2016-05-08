package me.kavzaq.qEssentialsReloaded.utils;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import com.google.common.collect.Lists;

import me.kavzaq.qEssentialsReloaded.impl.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.KitDataImpl;

public class SerializeUtils {
	
	private static StringBuilder strb = new StringBuilder();
	private SerializeUtils() { }
	
	//Kits
	public static String serializeKit(KitDataImpl kitData) {
		strb.setLength(0);
		strb.append(kitData.getName() + " ");
		strb.append(kitData.getCooldown());
		return strb.toString();
	}
	
	public static KitDataImpl deserializeKit(String kitData) {
		String[] split = kitData.split(" ");
		return new KitDataImpl(split[0], Long.valueOf(split[1]));
	}
	
	public static String serializeList(List<String> list) {
		strb.setLength(0);
		if (list.isEmpty()) {
			return "";
		}
		for (String str : list) {
			strb.append(str + ',');
		}
		return strb.toString().substring(0, strb.toString().length() - 1);
	}
	
	public static List<String> deserializeList(String list) {
		List<String> result = Lists.newArrayList();
		String[] split = list.split(",");
		for (String str : split) {
			result.add(str);
		}
		return result;
	}
	
	public static String serializeHome(HomeDataImpl homeData) {
		strb.setLength(0);
		strb.append(homeData.getName() + " ");
		strb.append(homeData.getLocation().getWorld().getName() + " ");
		strb.append(homeData.getLocation().getX() + " ");
		strb.append(homeData.getLocation().getY() + " ");
		strb.append(homeData.getLocation().getZ());
		return strb.toString();
	}
		 
	public static HomeDataImpl deserializeHome(String homeData) {
		String[] split = homeData.split(" ");
		World world = Bukkit.getServer().getWorld(split[1]);
		if (world == null) {
			return null;
		}
		return new HomeDataImpl(split[0], 
				new Location(world, Double.valueOf(split[2]), Double.valueOf(split[3]), Double.valueOf(split[4])));
	}

}
