package me.kavzaq.qEssentialsReloaded.commands.aliases;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class SunAlias extends CommandImpl  {

	public SunAlias() {
		super("sun", "Time alias", "/sun", "time.aliases", Lists.newArrayList());
		
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		World world = Bukkit.getWorlds().get(0);
		world.setTime(2500L);
		Util.sendMessage(s, MessagesImpl.TIME_DAY_SUCCESS.replace("%world%", world.getName()));
	}

}
