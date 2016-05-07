package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.timed.HelpopTimed;

public class HelpopCommand extends CommandImpl {

	public HelpopCommand() {
		super("helpop", "Helpop management", "/helpop <message>", "helpop", Arrays.asList("qhelpop", "adminhelp"));
	}

	private static final StringBuilder localsb = new StringBuilder();
	
	@Override
	public void onExecute(CommandSender s, String[] args) {
		if (args.length == 0) {
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		long _delay = Main.getInstance().getConfig().getLong("helpop-delay") * 1000;
		if (!s.hasPermission("qessentials.helpop.bypass")) {
			if (HelpopTimed.isBlocked(s)) {
				Util.sendMessage(s, MessagesImpl.HELPOP_COOLDOWN
						.replace("%cooldown%", HelpopTimed.timeRemain(s)));
				return;
			}
			HelpopTimed.setLastHelpop(s, System.currentTimeMillis() + _delay);
		}
		localsb.setLength(0);
		for (String str : args) {
			if (localsb.length() > 0) {
				localsb.append(" ");
			}
			localsb.append(str);
		}
		Bukkit.getConsoleSender().sendMessage(Util.fixColors(
				MessagesImpl.HELPOP_FORMAT
						.replace("%player%", s.getName())
						.replace("%message%", localsb.toString())));
		Util.sendMessage(s, MessagesImpl.HELPOP_FORMAT
				.replace("%player%", s.getName())
				.replace("%message%", localsb.toString()));
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.hasPermission("qessentials.helpop.receiver")) {
				Util.sendMessage(p, MessagesImpl.HELPOP_FORMAT
						.replace("%player%", s.getName())
						.replace("%message%", localsb.toString()));
			}
		}
		
	}

}
