package me.kavzaq.qEssentialsReloaded.commands.aliases;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import com.google.common.collect.Lists;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class ThunderAlias extends CommandImpl {
	
	public ThunderAlias() {
		super("thunder", "Weather alias", "/thunder", "weather.aliases", Lists.newArrayList());
		
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		World world = Bukkit.getWorlds().get(0);
		world.setWeatherDuration(999999999);
		world.setThunderDuration(999999999);
		world.setStorm(true);
		world.setThundering(true);
		Util.sendMessage(s, MessagesImpl.WEATHER_THUNDER_SUCCESS.replace("%world%", world.getName()));
	}


}
