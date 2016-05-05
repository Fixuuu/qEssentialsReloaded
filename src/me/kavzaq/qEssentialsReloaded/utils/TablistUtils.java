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
import net.dzikoysk.funnyguilds.basic.User;
import net.dzikoysk.funnyguilds.util.Parser;
import net.dzikoysk.funnyguilds.util.runnable.Ticking;

public class TablistUtils {
	
	private static List<String> strings;
	
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
	// {ONLINE}
	
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
		if (Main.funnyguilds_support) {
			User fgu = User.get(player);
			string = StringUtils.replace(string, "{TPS}", Ticking.getTPS());
			string = StringUtils.replace(string, "{PING}", Integer.toString(fgu.getPing()));
			string = StringUtils.replace(string, "{POINTS}", Integer.toString(fgu.getRank().getPoints()));
			string = StringUtils.replace(string, "{KILLS}", Integer.toString(fgu.getRank().getKills()));
			string = StringUtils.replace(string, "{DEATHS}", Integer.toString(fgu.getRank().getDeaths()));
			
			String parsedRank = Parser.parseRank(string);
			if (parsedRank != null) string = parsedRank;
		}
		return string;
		
	}

}
