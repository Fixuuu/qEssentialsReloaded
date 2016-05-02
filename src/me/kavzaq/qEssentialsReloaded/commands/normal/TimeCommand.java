package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class TimeCommand extends CommandImpl {

	public TimeCommand() {
		super("time", "Time management", "/time <day/night/<time>>", "time", Arrays.asList("qtime", "czas"));
		
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		World world = p.getWorld();
		if((args.length == 0) || (args.length >= 2)){
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 1){
			if(args[0].equalsIgnoreCase("day")){
				world.setTime(2500L);
				Util.sendMessage(p, MessagesImpl.TIME_DAY_SUCCESS.replace("%world%", world.getName()));
				return;
			} else if (args[0].equalsIgnoreCase("night")){
				world.setTime(14500L);
				Util.sendMessage(p, MessagesImpl.TIME_NIGHT_SUCCESS.replace("%world%", world.getName()));
				return;
			} else {
				if (!StringUtils.isNumeric(args[0])) {
					Util.sendMessage(p, MessagesImpl.MUST_BE_INT);
					return;
				}
				try {
					int time = Integer.valueOf(args[0]);
					world.setTime(time);
				} catch (NumberFormatException e) {
					args[0] = "69 because you're dumb and gave me a lot of numbers";
					world.setTime(69);
				}
				Util.sendMessage(p, MessagesImpl.TIME_OWN_SUCCESS.replace("%world%", world.getName())
						.replace("%time%", args[0]));
				return;
			}
		}
		
	}

}
