package me.kavzaq.qEssentialsReloaded.utils;

import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.KitDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.interfaces.Kit;

public class KitUtils {
	
	public static void takeKit(Kit kit, Player p) {
		UserImpl u = Main.getUserManager().getUser(p);
		if (getKitData(kit, p) != null) {
			u.delKit(getKitData(kit, p));
			u.save();
		}

		u.addKit(new KitDataImpl(kit.getName(), System.currentTimeMillis() + kit.getCooldown()));
		u.save();
	}
	
	public static KitDataImpl getKitData(Kit kit, Player p) {
		UserImpl u = Main.getUserManager().getUser(p);
		String _kit = null;
		for (String kitStr : u.getKits()) {
			if (kitStr.contains(kit.getName())) _kit = kitStr;
		}
		if (_kit == null) return null;
		KitDataImpl _kitData = SerializeUtils.deserializeKit(_kit);
		return _kitData;
	}
	
	public static boolean canTake(Kit kit, Player p) {
		if (getKitData(kit, p) == null) 
		{
			return true;
		}
		long timeTake = getKitData(kit, p).getCooldown() == null ? 0 : getKitData(kit, p).getCooldown();
		if(timeTake == 0) return true; 
		long timeCurrent = System.currentTimeMillis();
		if(timeTake > timeCurrent) {
			return false;
		}
		return true;
	}
	
	public static String timeRemain(Kit kit, Player p) {
		return Util.parseTime(((getKitData(kit, p).getCooldown() == null ? 0 : getKitData(kit, p).getCooldown() - System.currentTimeMillis())) + 1);
	}

}
