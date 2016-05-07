package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.HomeDataImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.utils.ListingUtils;
import me.kavzaq.qEssentialsReloaded.utils.SerializeUtils;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class HomeCommand extends CommandImpl {

	public HomeCommand() {
		super("home", "Teleports to a home", "/home [name]", "home", Arrays.asList("qhome", "dom"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		if (args.length >= 2) {
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		Player p = (Player)s;
		UserImpl u = Main.getUserManager().getUser(p);
		
		if (args.length == 0) {
			Util.sendMessage(p, MessagesImpl.HOME_LIST_HEADER);
			Util.sendMessage(p, ListingUtils.getHomeList(p));
			return;
		}
		
		else if (args.length == 1) {
			String homeName = args[0];
			String _home = null;
			for (String home : u.getHomes()) {
				if (home.contains(homeName)) _home = home;
			}
			if ((_home == null) || (u.getHomes().size() == 0)) {
				Util.sendMessage(p, MessagesImpl.HOME_UNKNOWN);
				return;
			}
			HomeDataImpl homeData = SerializeUtils.deserializeHome(_home);
			TeleportUtils tpu = new TeleportUtils(p);
			tpu.teleport(homeData.getLocation());
			Util.sendMessage(p, MessagesImpl.HOME_SUCCESS
					.replace("%home%", homeData.getName()));
			return;
		}
	}

}
