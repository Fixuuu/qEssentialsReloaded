package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class SetHomeCommand extends CommandImpl {

	public SetHomeCommand() {
		super("sethome", "Sets a new home", "/sethome <name>", "sethome", Arrays.asList("qsethome", "ustawdom"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if ((args.length == 0) || (args.length >= 2)) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		UserImpl u = Main.getUserManager().getUser(p);
		
		int maxhomes = Main.getInstance().getConfig().getInt("max-homes") - 1;
		if (u.getHomes().size() > maxhomes) {
			Util.sendMessage(p, MessagesImpl.SETHOME_MAX);
			return;
		}
		
		for (String home : u.getHomes()) {
			if (home.contains(args[0])){
				Util.sendMessage(p, MessagesImpl.SETHOME_HAS_THIS_NAME);
				return;
			}
		}
		
		u.addHome(new HomeDataImpl(args[0], p.getLocation()));
		u.save();
		
		Util.sendMessage(p, MessagesImpl.SETHOME_SUCCESS
				.replace("%home%", args[0]));
	}

}
