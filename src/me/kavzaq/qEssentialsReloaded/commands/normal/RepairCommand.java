package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.RepairUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class RepairCommand extends CommandImpl {

	public RepairCommand() {
		super("repair", "Repairs an items", "/repair [all/armor]", "repair", Arrays.asList("qrepair"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if (args.length >= 2) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 0) {
			RepairUtils.repair(p);
			return;
		}
		else if (args.length == 1) {
			if (args[0].equalsIgnoreCase("all")) {
				if (!p.hasPermission("qessentials.repair.all")) {
					Util.sendMessage(p, MessagesImpl.NO_PERMISSION
							.replace("%permission%", "qessentials.repair.all"));
					return;
				}
				RepairUtils.repairAll(p);
				return;
			}
			else if (args[0].equalsIgnoreCase("armor")) {
				if (!p.hasPermission("qessentials.repair.armor")) {
					Util.sendMessage(p, MessagesImpl.NO_PERMISSION
							.replace("%permission%", "qessentials.repair.armor"));
					return;
				}
				RepairUtils.repairArmor(p);
				return;
			}
			else {
				Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
				return;
			}
		}
		
	}

}
