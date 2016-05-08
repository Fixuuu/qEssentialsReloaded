package me.kavzaq.qEssentialsReloaded.utils;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.TabConfigurationImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.runnables.tpsmonitor.TPSMonitor;
import net.dzikoysk.funnyguilds.FunnyGuilds;
import net.dzikoysk.funnyguilds.basic.Guild;
import net.dzikoysk.funnyguilds.basic.User;
import net.dzikoysk.funnyguilds.basic.util.RankManager;
import net.dzikoysk.funnyguilds.data.Settings;
import net.dzikoysk.funnyguilds.util.runnable.Ticking;

public class TablistUtils {
	
	private static List<String> strings;
	private TablistUtils() { }
	
	public static void configureMessages() {
		strings = Lists.newArrayList();
		for(Field f : TabConfigurationImpl.class.getFields()) {
			String name = f.getName();
			if(name.startsWith("tabSlot_")) {
				try {
					strings.add(f.get(f.getName()).toString());
				} catch (IllegalArgumentException | IllegalAccessException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	public static void updateTab(Player p) {
		int message = 0;
		for (int i = 0; i <= 19; i++) {
			Main.getTabExecutor().updateSlot(p, i, 0, Util.fixColors(replaceVariables(p, strings.get(message))));
			message++;
		}
		for (int i = 0; i <= 19; i++) {
			Main.getTabExecutor().updateSlot(p, i, 1, Util.fixColors(replaceVariables(p, strings.get(message))));
			message++;
		}
		for (int i = 0; i <= 19; i++) {
			Main.getTabExecutor().updateSlot(p, i, 2, Util.fixColors(replaceVariables(p, strings.get(message))));
			message++;
		}
		for (int i = 0; i <= 19; i++) {
			if (message >= 79) {
				Main.getTabExecutor().updateSlot(p, i, 3, Util.fixColors(replaceVariables(p, strings.get(78))));
				break;
			}
			Main.getTabExecutor().updateSlot(p, i, 3, Util.fixColors(replaceVariables(p, strings.get(message))));
			message++;
		}
		Main.getTabExecutor().executeTab(p);
	}
	
	public static void showTab(Player p) {
		Main.getTabExecutor().clearTab(p);		
		Main.getTabManager().sendPacketHeaderFooter(p, 
				TabConfigurationImpl.tablistHeader, TabConfigurationImpl.tablistFooter);

		int message = 0;
		for (int i = 0; i <= 19; i++) {
			Main.getTabExecutor().addSlot(p, i, 0, Util.fixColors(replaceVariables(p, strings.get(message))));
			message++;
		}
		for (int i = 0; i <= 19; i++) {
			Main.getTabExecutor().addSlot(p, i, 1, Util.fixColors(replaceVariables(p, strings.get(message))));
			message++;
		}
		for (int i = 0; i <= 19; i++) {
			Main.getTabExecutor().addSlot(p, i, 2, Util.fixColors(replaceVariables(p, strings.get(message))));
			message++;
		}
		for (int i = 0; i <= 19; i++) {
			if (message >= 79) {
				Main.getTabExecutor().addSlot(p, i, 3, Util.fixColors(replaceVariables(p, strings.get(78))));
				break;
			}
			Main.getTabExecutor().addSlot(p, i, 3, Util.fixColors(replaceVariables(p, strings.get(message))));
			message++;
		}
		Main.getTabExecutor().executeTab(p);
	}
	
	// variables from qEssentialsReloaded
	
	// {PLAYER} - player name
	// {DISPLAYNAME} - player displayname
	// {GOD} - god enabled?
	// current {HOUR} - hours, {MINUTE} - minutes, {SECOND}
	// current {DATE} - date: 00.00.0000, DAYS.MONTHS.YEAR
	// {FOOD} - food amount
	// {HEALTH} - health amount
	// {EXP} - exp amount
	// {EXP-TO-LVLUP} - exp required to levelup
	// {LEVEL} - level
	// {TOTAL-EXP} - total experience
	// {WORLD} - player world
	// {FLY} - is flying
	// {OP} - is op
	// {WHITELISTED} - is whitelisted
	// {ONLINE} - online players
	// {TPS} - ticking monitor 
	
	// variables from FunnyGuilds
	// {FG/TPS} - funnyguilds ticking monitor
	// {FG/PING} - player ping
	// {FG/POINTS} - rank
	// {FG/KILLS} - kills
	// {FG/DEATHS} - deaths
	// {FG/GTOP-x} - top guilds
	// {FG/PTOP-x} - top players
	
	// VARIABLE SUPPORT FROM GUILDS, RANKINGS ITD. WILL BE ADDED SOON (WHEN PLUGINS ON 1.9 WILL COME ON)
	// variable support now:
	//   - FunnyGuilds
	
	public static String replaceVariables(Player player, String string) {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		UserImpl user = Main.getUserManager().getUser(player);
		string = StringUtils.replace(string, "{PLAYER}", player.getName());
		string = StringUtils.replace(string, "{DISPLAYNAME}", player.getDisplayName());
		string = StringUtils.replace(string, "{ONLINE}", String.valueOf(Bukkit.getOnlinePlayers().size()));
		
		string = StringUtils.replace(string, "{FOOD}", String.valueOf(player.getFoodLevel()));
		string = StringUtils.replace(string, "{HEALTH}", String.valueOf(player.getHealth()));
		// time
		if (hour > 10) string = StringUtils.replace(string, "{HOUR}", String.valueOf(hour));
		else string = StringUtils.replace(string, "{HOUR}", "0" + String.valueOf(hour));
		if (minute > 10) string = StringUtils.replace(string, "{MINUTE}", String.valueOf(minute));
		else string = StringUtils.replace(string, "{MINUTE}", "0" + String.valueOf(minute));
		if (second > 10) string = StringUtils.replace(string, "{SECOND}", String.valueOf(second));
		else string = StringUtils.replace(string, "{SECOND}", "0" + String.valueOf(second));
		string = StringUtils.replace(string, "{DATE}", Util.formattedTime(System.currentTimeMillis()));
		// player info
		string = StringUtils.replace(string, "{EXP}", String.valueOf(Util.round(player.getExp(), 1)));
		string = StringUtils.replace(string, "{EXP-TO-LVLUP}", String.valueOf(player.getExpToLevel()));
		string = StringUtils.replace(string, "{LEVEL}", String.valueOf(player.getLevel()));
		string = StringUtils.replace(string, "{TOTAL-EXP}", String.valueOf(player.getTotalExperience()));
		string = StringUtils.replace(string, "{WORLD}", player.getWorld().getName());
		// boolean
		string = StringUtils.replace(string, "{FLY}", BooleanUtils.getParsedBooleanYesNo(player.isFlying()));
		string = StringUtils.replace(string, "{OP}", BooleanUtils.getParsedBooleanYesNo(player.isOp()));
		string = StringUtils.replace(string, "{GOD}", BooleanUtils.getParsedBooleanYesNo(user.isGod()));
		string = StringUtils.replace(string, "{WHITELISTED}", BooleanUtils.getParsedBooleanYesNo(player.isWhitelisted()));
		
		String tps = String.valueOf(TPSMonitor.getCurrentTPS()).contains("-1.0") ? "Calculating..." : 
			String.valueOf(TPSMonitor.getCurrentTPS()); 
		
		string = StringUtils.replace(string, "{TPS}", tps);
		
		if (Main.funnyguilds_support) {
			User fgu = User.get(player);
			string = StringUtils.replace(string, "{FG/TPS}", Ticking.getTPS());
			string = StringUtils.replace(string, "{FG/PING}", Integer.toString(fgu.getPing()));
			string = StringUtils.replace(string, "{FG/POINTS}", Integer.toString(fgu.getRank().getPoints()));
			string = StringUtils.replace(string, "{FG/KILLS}", Integer.toString(fgu.getRank().getKills()));
			string = StringUtils.replace(string, "{FG/DEATHS}", Integer.toString(fgu.getRank().getDeaths()));
			
			String parsedRank = parseRank(string);
			if (parsedRank != null) string = parsedRank;
		}
		return string;
		
	}
	
	// FunnyGuilds method / rank support
	public static String parseRank(String string) {
		if (!string.contains("TOP-")) return null;
		StringBuilder sb = new StringBuilder();
		boolean open = false;
		boolean start = false;
		for (char c : string.toCharArray()) {
			boolean end = false;
			switch (c) {
			case '{':
				open = true;
				break;
			case '-':
				start = true;
				break;
			case '}':
				end = true;
				break;
			default:
				if (open && start) sb.append(c);
			}
			if (end) break;
		}
		try {
			int i = Integer.valueOf(sb.toString());
			if (string.contains("FG/GTOP")) {
				Guild guild = RankManager.getInstance().getGuild(i);
				if (guild != null) return StringUtils
						.replace(string, "{FG/GTOP-" + Integer.toString(i) + '}',
								guild.getTag() + " " +
										StringUtils.replace(Settings.getInstance().playerlistPoints,
												"{FG/POINTS}", Integer.toString(guild.getRank().getPoints()))
								);
				else return StringUtils
						.replace(string, "{FG/GTOP-" + Integer.toString(i) + '}', "Brak");
			} else if (string.contains("FG/PTOP")) {
				User user = RankManager.getInstance().getUser(i);
				if (user != null) return StringUtils
						.replace(string, "{FG/PTOP-" + Integer.toString(i) + '}', user.getName());
				else return StringUtils
						.replace(string, "{FG/PTOP-" + Integer.toString(i) + '}', "Brak");
			}
		} catch (NumberFormatException e) {
			FunnyGuilds.parser("Unknown number: " + sb.toString());
		}
		return null;
	}

}
