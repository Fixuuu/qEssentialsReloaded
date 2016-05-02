package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.utils.BooleanUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.switches.GodSwitch;

public class GodCommand extends CommandImpl {

	public GodCommand() {
		super("god", "Enables/disabled god mode", "/god [player]", "god", Arrays.asList("qgod", "godmode", "bog"));
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
			UserImpl u = Main.getUserManager().getUser(p);
			boolean godState = GodSwitch.switchGod(u);
			p.setHealth(20.0);
			p.setFoodLevel(20);
			u.setGod(godState);
			Util.sendMessage(p, MessagesImpl.GOD_SWITCHED
					.replace("%mode%", BooleanUtils.getParsedBoolean(godState)));
			return;
		}
		else if (args.length == 1) {
			if(s.hasPermission("qessentials.god.others")){
				if (Bukkit.getPlayer(args[0]) == null) {
					Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
					return;
				}
				Player other = Bukkit.getPlayer(args[0]);
				if (s.equals(other)) {
					Util.sendMessage(s, MessagesImpl.SAME_PERSON);
					return;
				}
				UserImpl otheru = Main.getUserManager().getUser(other);
				boolean godState = GodSwitch.switchGod(otheru);
				other.setHealth(20.0);
				other.setFoodLevel(20);
				otheru.setGod(godState);
				Util.sendMessage(s, MessagesImpl.GOD_OTHER_SUCCESS
						.replace("%mode%", BooleanUtils.getParsedBoolean(godState))
						.replace("%player%", other.getName()));
				Util.sendMessage(other, MessagesImpl.GOD_OTHER_ACTIVATED
						.replace("%mode%", BooleanUtils.getParsedBoolean(godState))
						.replace("%player%", s.getName()));
				return;
			}else{
				Util.sendMessage(s, MessagesImpl.NO_PERMISSION.replace("%permission%", "qessentials.god.others"));
			}
			return;
		}
		
	}

}
