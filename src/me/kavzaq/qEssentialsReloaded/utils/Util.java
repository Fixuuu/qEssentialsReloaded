package me.kavzaq.qEssentialsReloaded.utils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import me.kavzaq.qEssentialsReloaded.interfaces.User;

public class Util {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
	private static final StringBuilder sb = new StringBuilder();
	private Util() { }
	
	public static String fixColors(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
	
	public static List<String> fixColoredList(List<String> coloredList) {
		final List<String> list = Lists.newArrayList();
		for (String str : coloredList) list.add(fixColors(str));
		return list;
	}
	
	public static String readUrl(String url) throws Exception {
		InputStream in = new URL(url).openStream();

		try {
			return IOUtils.toString(in);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
	
	public static boolean isFieldList(Field field)
	{
		return List.class.isAssignableFrom(field.getType());
	}
	
	public static void sendMessage(Player p, String s) {
		p.sendMessage(fixColors(s));
	}
	
	public static void sendMessage(CommandSender sender, String s) {
		sender.sendMessage(fixColors(s));
	}
	
	public static void sendMessage(User u, String s) {
		Bukkit.getPlayer(u.getUUID()).sendMessage(fixColors(s));
	}

	public static boolean getChance(double chance) {
		return (Math.random() < chance);
	}

	public static String formattedTime(long time) {
	    return dateFormat.format(new Date(time));
	}
	
	public static double round(double d, int places) {
		BigDecimal bdec = new BigDecimal(d);
		bdec = bdec.setScale(places, RoundingMode.HALF_UP);
		return bdec.doubleValue();
	}
	
	
	/*
	 * @AUTHOR Dzikoysk
	 * https://github.com/dzikoysk/FunnyGuilds/blob/master/src/main/java/net/dzikoysk/funnyguilds/util/TimeUtils.java
	 * 
	 * Dostalem kurwicy jak to robilem sam, nadal nie umiem zarzadzac czasem w javie.
	 */
	public static String parseTime(long millis) {
		if (millis == 0) return "0";
		sb.setLength(0);

		long days = TimeUnit.MILLISECONDS.toDays(millis);
		if (days > 0) millis -= TimeUnit.DAYS.toMillis(days);
		long hours = TimeUnit.MILLISECONDS.toHours(millis);
		if (hours > 0) millis -= TimeUnit.HOURS.toMillis(hours);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
		if (minutes > 0) millis -= TimeUnit.MINUTES.toMillis(minutes);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
		if (seconds > 0) millis -= TimeUnit.SECONDS.toMillis(seconds);

		if (days > 0) {
			sb.append(days);
			long i = days % 10;
			if (i == 1) sb.append(" dzien ");
			else sb.append(" dni ");
		}
		if (hours > 0) {
			sb.append(hours);
			long i = hours % 10;
			if (i == 1) sb.append(" godzine ");
			else if (i < 5) sb.append(" godziny ");
			else sb.append(" godzin ");
		}
		if (minutes > 0) {
			sb.append(minutes);
			long i = minutes % 10;
			if (i == 1) sb.append(" minute ");
			else if (i < 5) sb.append(" minuty ");
			else sb.append(" minut ");
		}
		if (seconds > 0) {
			sb.append(seconds);
			long i = seconds % 10;
			if (i == 1) sb.append(" sekunde ");
			else if (i < 5) sb.append(" sekundy ");
			else sb.append(" sekund ");
		}
		return (sb.toString());
	}

}
