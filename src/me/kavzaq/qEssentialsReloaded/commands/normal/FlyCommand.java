package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.BooleanUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.switches.FlySwitch;

public class FlyCommand extends CommandImpl {

	public FlyCommand() {
		super("fly", "Allows or not player to fly", "/fly [player]", "fly", Arrays.asList("qfly", "latanie"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		if (args.length >= 2) {
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 0) {
			if (!(s instanceof Player)) {
				Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
				return;
			}
			Player p = (Player)s;
			boolean flyState = FlySwitch.switchFlying(p);
			p.setAllowFlight(flyState);
			Util.sendMessage(p, MessagesImpl.FLY_SWITCHED
					.replace("%mode%", BooleanUtils.getParsedBoolean(flyState)));
			return;
		}
		else if (args.length == 1) {
			if(s.hasPermission("qessentials.fly.others")){
				if (Bukkit.getPlayer(args[0]) == null) {
					Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
					return;
				}
				Player other = Bukkit.getPlayer(args[0]);
				if (s.equals(other)) {
					Util.sendMessage(s, MessagesImpl.SAME_PERSON);
					return;
				}
				boolean flyState = FlySwitch.switchFlying(other);
				other.setAllowFlight(flyState);
				Util.sendMessage(s, MessagesImpl.FLY_OTHER_SUCCESS
						.replace("%mode%", BooleanUtils.getParsedBoolean(flyState))
						.replace("%player%", other.getName()));
				Util.sendMessage(other, MessagesImpl.FLY_OTHER_SWITCHED
						.replace("%mode%", BooleanUtils.getParsedBoolean(flyState))
						.replace("%player%", s.getName()));
				return;
			}else{
				Util.sendMessage(s, MessagesImpl.NO_PERMISSION.replace("%permission%", "qessentials.fly.others"));
			}
			return;
		}
	}

}
