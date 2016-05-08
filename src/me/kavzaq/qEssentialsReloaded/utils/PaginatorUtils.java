package me.kavzaq.qEssentialsReloaded.utils;

import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.bukkit.command.CommandSender;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;

public class PaginatorUtils {
	
	private PaginatorUtils() { }
	public static SortedMap<Integer, String> map = new TreeMap<Integer, String>();
	
	// z bukkit forums, bo jestem slaby w paginowaniu ;d
	public static void paginateHelp(CommandSender p, int page, int pageLength) {
		int size = getSize(pageLength);
		Util.sendMessage(p, MessagesImpl.HELP_HEADER
				.replace("%page%", String.valueOf(page))
				.replace("%pages%", String.valueOf(size)));
		int i = 0, k = 0;
		page--;
		for (Entry<Integer, String> e : map.entrySet()) {
			k++;
			if ((((page * pageLength) + i + 1) == k) && (k != ((page * pageLength) + pageLength + 1))) {
				i++;
				Util.sendMessage(p, e.getValue());
			}
		}
	}
	
	public static int getSize(int pageLength) {
		return (((map.size() % pageLength) == 0) ? map.size() / pageLength : (map.size() / pageLength) + 1);
	}
	
	public static boolean containsPage(int page, int pageLength) {
		return page <= getSize(pageLength);
	}
	
	// lagozerny potwor XDDDD
	public static void configureHelp() {
		map.put(0, MessagesImpl.HELP_INDEX.replace("%command%", "back").replace("%description%", MessagesImpl.BACK_DESC));
		map.put(1, MessagesImpl.HELP_INDEX.replace("%command%", "broadcast").replace("%description%", MessagesImpl.BROADCAST_DESC));
		map.put(2, MessagesImpl.HELP_INDEX.replace("%command%", "chat").replace("%description%", MessagesImpl.CHAT_DESC));
		map.put(3, MessagesImpl.HELP_INDEX.replace("%command%", "clearinventory").replace("%description%", MessagesImpl.CLEARINV_DESC));
		map.put(4, MessagesImpl.HELP_INDEX.replace("%command%", "delhome").replace("%description%", MessagesImpl.DELHOME_DESC));
		map.put(5, MessagesImpl.HELP_INDEX.replace("%command%", "feed").replace("%description%", MessagesImpl.FEED_DESC));
		map.put(6, MessagesImpl.HELP_INDEX.replace("%command%", "fly").replace("%description%", MessagesImpl.FLY_DESC));
		map.put(7, MessagesImpl.HELP_INDEX.replace("%command%", "gamemode").replace("%description%", MessagesImpl.GAMEMODE_DESC));
		map.put(8, MessagesImpl.HELP_INDEX.replace("%command%", "god").replace("%description%", MessagesImpl.GOD_DESC));
		map.put(9, MessagesImpl.HELP_INDEX.replace("%command%", "heal").replace("%description%", MessagesImpl.HEAL_DESC));
		map.put(10, MessagesImpl.HELP_INDEX.replace("%command%", "help").replace("%description%", MessagesImpl.HELP_DESC));
		map.put(11, MessagesImpl.HELP_INDEX.replace("%command%", "home").replace("%description%", MessagesImpl.HOME_DESC));
		map.put(12, MessagesImpl.HELP_INDEX.replace("%command%", "item").replace("%description%", MessagesImpl.ITEM_DESC));
		map.put(13, MessagesImpl.HELP_INDEX.replace("%command%", "list").replace("%description%", MessagesImpl.LIST_DESC));
		map.put(14, MessagesImpl.HELP_INDEX.replace("%command%", "message").replace("%description%", MessagesImpl.MESSAGE_DESC));
		map.put(15, MessagesImpl.HELP_INDEX.replace("%command%", "reply").replace("%description%", MessagesImpl.REPLY_DESC));
		map.put(16, MessagesImpl.HELP_INDEX.replace("%command%", "sethome").replace("%description%", MessagesImpl.SETHOME_DESC));
		map.put(17, MessagesImpl.HELP_INDEX.replace("%command%", "setspawn").replace("%description%", MessagesImpl.SETSPAWN_DESC));
		map.put(18, MessagesImpl.HELP_INDEX.replace("%command%", "spawn").replace("%description%", MessagesImpl.SPAWN_DESC));
		map.put(19, MessagesImpl.HELP_INDEX.replace("%command%", "time").replace("%description%", MessagesImpl.TIME_DESC));
		map.put(20, MessagesImpl.HELP_INDEX.replace("%command%", "tpaccept").replace("%description%", MessagesImpl.TP_ACC_DESC));
		map.put(21, MessagesImpl.HELP_INDEX.replace("%command%", "tpdeny").replace("%description%", MessagesImpl.TP_DENY_DESC));
		map.put(22, MessagesImpl.HELP_INDEX.replace("%command%", "tpa").replace("%description%", MessagesImpl.TPA_DESC));
		map.put(23, MessagesImpl.HELP_INDEX.replace("%command%", "tp").replace("%description%", MessagesImpl.TP_DESC));
		map.put(24, MessagesImpl.HELP_INDEX.replace("%command%", "weather").replace("%description%", MessagesImpl.WEATHER_DESC));
		map.put(25, MessagesImpl.HELP_INDEX.replace("%command%", "whois").replace("%description%", MessagesImpl.WHOIS_DESC));
		map.put(26, MessagesImpl.HELP_INDEX.replace("%command%", "world").replace("%description%", MessagesImpl.WORLD_DESC));
		map.put(27, MessagesImpl.HELP_INDEX.replace("%command%", "kit").replace("%description%", MessagesImpl.KIT_DESC));
		map.put(28, MessagesImpl.HELP_INDEX.replace("%command%", "give").replace("%description%", MessagesImpl.GIVE_DESC));
		map.put(29, MessagesImpl.HELP_INDEX.replace("%command%", "enchant").replace("%description%", MessagesImpl.ENCHANT_DESC));
		map.put(30, MessagesImpl.HELP_INDEX.replace("%command%", "invsee").replace("%description%", MessagesImpl.INVSEE_DESC));
		map.put(31, MessagesImpl.HELP_INDEX.replace("%command%", "helpop").replace("%description%", MessagesImpl.HELPOP_DESC));
		map.put(32, MessagesImpl.HELP_INDEX.replace("%command%", "head").replace("%description%", MessagesImpl.HEAD_DESC));
		map.put(33, MessagesImpl.HELP_INDEX.replace("%command%", "enderchest").replace("%description%", MessagesImpl.ENDERCHEST_DESC));
		map.put(34, MessagesImpl.HELP_INDEX.replace("%command%", "garbagecollector").replace("%description%", MessagesImpl.GARBAGECOLLECTOR_DESC));
		map.put(35, MessagesImpl.HELP_INDEX.replace("%command%", "repair").replace("%description%", MessagesImpl.REPAIR_DESC));
		map.put(36, MessagesImpl.HELP_INDEX.replace("%command%", "kick").replace("%description%", MessagesImpl.KICK_DESC));
		map.put(37, MessagesImpl.HELP_INDEX.replace("%command%", "hat").replace("%description%", MessagesImpl.HAT_DESC));
		map.put(38, MessagesImpl.HELP_INDEX.replace("%command%", "displayname").replace("%description%", MessagesImpl.DISPLAYNAME_DESC));
	}

}
