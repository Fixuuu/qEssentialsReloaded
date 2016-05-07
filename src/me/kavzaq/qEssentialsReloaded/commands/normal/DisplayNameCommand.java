package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class DisplayNameCommand extends CommandImpl {

	public DisplayNameCommand() {
		super("displayname", "Sets a player displayname", "/displayname [<displayname>/reset] [player]", "displayname", 
				Arrays.asList("display", "qdisplayname", "qdisplay"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if ((args.length >= 3) || (args.length == 0)) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
		}
		else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reset")) {
				p.setDisplayName(p.getName());
				Util.sendMessage(p, MessagesImpl.DISPLAYNAME_RESET);
				return;
			}
			String displayname = Util.fixColors(args[0]);
			p.setDisplayName(displayname);
			Util.sendMessage(p, MessagesImpl.DISPLAYNAME_SUCCESS
					.replace("%displayname%", displayname));
		}
		
		else if (args.length == 2) {
			if (!p.hasPermission("qessentials.displayname.others")) {
				Util.sendMessage(p, MessagesImpl.NO_PERMISSION
						.replace("%permission%", "qessentials.displayname.others"));
				return;
			}
			if (Bukkit.getPlayer(args[1]) == null) {
				Util.sendMessage(p, MessagesImpl.OFFLINE_PLAYER);
				return;
			}
			Player other = Bukkit.getPlayer(args[1]);
			if (other.getName().equals(p.getName())) {
				Util.sendMessage(p, MessagesImpl.SAME_PERSON);
				return;
			}
			if (args[0].equalsIgnoreCase("reset")) {
				other.setDisplayName(other.getName());
				Util.sendMessage(p, MessagesImpl.DISPLAYNAME_OTHER_RESET_SUCCESS
						.replace("%player%", other.getName()));
				Util.sendMessage(other, MessagesImpl.DISPLAYNAME_OTHER_RESET_RESETED
						.replace("%player%", p.getName()));
				return;
			}
			else {
				String displayname = Util.fixColors(args[0]);
				other.setDisplayName(displayname);
				Util.sendMessage(p, MessagesImpl.DISPLAYNAME_OTHER_SUCCESS
						.replace("%player%", other.getName())
						.replace("%displayname%", displayname));
				Util.sendMessage(other, MessagesImpl.DISPLAYNAME_OTHER_CHANGED
						.replace("%player%", p.getName())
						.replace("%displayname%", displayname));
			}
		}
		return;
	}

}
