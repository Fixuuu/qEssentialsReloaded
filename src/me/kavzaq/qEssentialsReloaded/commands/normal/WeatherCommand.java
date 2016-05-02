package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class WeatherCommand extends CommandImpl {

	public WeatherCommand() {
		super("weather", "Sets a server weather", "/weather <sun/thunder>", "weather", Arrays.asList("qweather", "pogoda"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		World world = Bukkit.getWorlds().get(0);
		if((args.length >= 2) || (args.length == 0)){
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 1){
			if(args[0].equalsIgnoreCase("sun")){
				world.setWeatherDuration(999999999);
				world.setThunderDuration(999999999);
				world.setStorm(false);
				world.setThundering(false);
				Util.sendMessage(p, MessagesImpl.WEATHER_SUNNY_SUCCESS.replace("%world%", world.getName()));
				return;
			} else if (args[0].equalsIgnoreCase("thunder")){
				world.setWeatherDuration(999999999);
				world.setThunderDuration(999999999);
				world.setStorm(true);
				world.setThundering(true);
				Util.sendMessage(p, MessagesImpl.WEATHER_THUNDER_SUCCESS.replace("%world%", world.getName()));
				return;
			} else {
				Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
				return;
			}
		}
	}

}
