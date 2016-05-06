package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class InvseeCommand extends CommandImpl {

	public InvseeCommand() {
		super("invsee", "Shows a someones inventory", "/invsee <player>", "invsee", Arrays.asList("qinvsee", "inv"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if ((args.length == 0) || (args.length >= 2)) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (Bukkit.getPlayer(args[0]) == null) {
			Util.sendMessage(p, MessagesImpl.OFFLINE_PLAYER);
			return;
		}
		Player other = Bukkit.getPlayer(args[0]);
		p.openInventory(other.getInventory());
	}

}
