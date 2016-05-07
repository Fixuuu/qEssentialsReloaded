package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class EnderchestCommand extends CommandImpl {

	public EnderchestCommand() {
		super("enderchest", "Enderchest management", "/enderchest [player]", "enderchest", Arrays.asList("qenderchest", "ender", "ec"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if (args.length >= 2) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 0) {
			p.openInventory(p.getEnderChest());
			return;
		}
		else if (args.length == 1) {
			if (!p.hasPermission("qessentials.enderchest.others")) {
				Util.sendMessage(p, MessagesImpl.NO_PERMISSION
						.replace("%permission%", "qessentials.enderchest.others"));
				return;
			}
			if (Bukkit.getPlayer(args[0]) == null) {
				Util.sendMessage(p, MessagesImpl.OFFLINE_PLAYER);
				return;
			}
			Player other = Bukkit.getPlayer(args[0]);
			p.openInventory(other.getEnderChest());
			return;
		}
	}

}
