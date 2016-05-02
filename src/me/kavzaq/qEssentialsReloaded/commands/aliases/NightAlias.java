package me.kavzaq.qEssentialsReloaded.commands.aliases;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class NightAlias extends CommandImpl {
	
	public NightAlias() {
		super("night", "Time alias", "/night", "time.aliases", Lists.newArrayList());
		
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		World world = Bukkit.getWorlds().get(0);
		world.setTime(14500L);
		Util.sendMessage(s, MessagesImpl.TIME_NIGHT_SUCCESS.replace("%world%", world.getName()));
	}

}
