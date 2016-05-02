package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class BackCommand extends CommandImpl {

	public BackCommand() {
		super("back", "Teleports player to last location", "/back [player]", "back", Arrays.asList("qback", "powrot"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if (args.length >= 2) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 0) {
			Location last = Main.getTeleportUpdater().getLocation(p);
			new TeleportUtils(p).teleport(last);
			Util.sendMessage(p, MessagesImpl.BACK_SUCCESS);
			return;
		} 
		else if (args.length == 1) {
			if(s.hasPermission("qessentials.back.others")){
				if (Bukkit.getPlayer(args[0]) == null) {
					Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
					return;
				}
				Player other = Bukkit.getPlayer(args[0]);
				if (p.equals(other)) {
					Util.sendMessage(p, MessagesImpl.SAME_PERSON);
					return;
				}
				Location last = Main.getTeleportUpdater().getLocation(other);
				new TeleportUtils(p).teleport(last);
				Util.sendMessage(p, MessagesImpl.BACK_OTHER.replace("%player%", other.getName()));
				return;
			}else{
				Util.sendMessage(s, MessagesImpl.NO_PERMISSION.replace("%permission%", "qessentials.back.others"));
			}
		}
		
	}

}
