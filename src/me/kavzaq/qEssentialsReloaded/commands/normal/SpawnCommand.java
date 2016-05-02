package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class SpawnCommand extends CommandImpl {

	public SpawnCommand() {
		super("spawn", "Teleports player to a spawn", "/spawn [player]", "spawn", Arrays.asList("qspawn"));
	}
	
	@Override
	public void onExecute(CommandSender s, String[] args) {
		if(args.length >= 2){
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if(args.length == 0){
			if(!(s instanceof Player)){
				Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
				return;
			}
			Player p = (Player)s;
			new TeleportUtils(p).teleport(p.getWorld().getSpawnLocation());
			Util.sendMessage(p, MessagesImpl.TELEPORT_SUCCESS);
			return;
		}
		if(args.length == 1){
			if (Bukkit.getPlayer(args[0]) == null) {
				Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
				return;
			}
			Player other = Bukkit.getPlayer(args[0]);
			if (s.equals(other)) {
				Util.sendMessage(s, MessagesImpl.SAME_PERSON);
				return;
			}
			new TeleportUtils(other).teleport(other.getWorld().getSpawnLocation());
			Util.sendMessage(s, MessagesImpl.SPAWN_OTHER_SUCCESS
					.replace("%player%", other.getName()));
			Util.sendMessage(other, MessagesImpl.SPAWN_OTHER_TELEPORTED
					.replace("%player%", s.getName()));
			
		}
	}
}
