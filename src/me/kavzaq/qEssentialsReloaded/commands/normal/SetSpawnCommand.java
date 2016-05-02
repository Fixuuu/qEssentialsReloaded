package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class SetSpawnCommand extends CommandImpl {

	public SetSpawnCommand() {
		super("setspawn", "Sets a spawn", "/setspawn", "setspawn", Arrays.asList("qsetspawn", "ustawspawn"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		Location loc = p.getLocation();
		World w = p.getWorld();
		w.setSpawnLocation(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
		String coords = "x" + loc.getBlockX() + " y" + loc.getBlockY() + " z" + loc.getBlockY();
		Util.sendMessage(p, MessagesImpl.SETSPAWN_SUCCESS
				.replace("%coords%", coords)
				.replace("%world%", w.getName()));
	}

}
