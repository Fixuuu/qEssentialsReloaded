package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.timed.TemporaryBanTimed;

public class KickCommand extends CommandImpl {

	public KickCommand() {
		super("kick", "Kicks a player", "/kick <player> [reason]", "kick", Arrays.asList("qkick", "wyrzuc"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		if (args.length == 0) {
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 1) {
			if (Bukkit.getPlayer(args[0]) == null) {
				Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
				return;
			}
			Player other = Bukkit.getPlayer(args[0]);
			TemporaryBanTimed.setLastTempBan(other.getUniqueId(), System.currentTimeMillis() + 
					Main.getInstance().getConfig().getLong("kick-temporary-ban") * 1000);

			other.kickPlayer(Util.fixColors(MessagesImpl.KICK_DEFAULT_REASON));
			Bukkit.broadcastMessage(Util.fixColors(MessagesImpl.KICK_BROADCAST
					.replace("%player%", other.getName())
					.replace("%reason%", MessagesImpl.KICK_DEFAULT_REASON)));
		
			return;
		}
		else {
			if (Bukkit.getPlayer(args[0]) == null) {
				Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
				return;
			}
			Player other = Bukkit.getPlayer(args[0]);
			TemporaryBanTimed.setLastTempBan(other.getUniqueId(), System.currentTimeMillis() + 
					Main.getInstance().getConfig().getLong("kick-temporary-ban") * 1000);
			other.kickPlayer(Util.fixColors(MessagesImpl.KICK_FORMAT
					.replace("%reason%", StringUtils.join(args, " ", 1, args.length))));
			Bukkit.broadcastMessage(Util.fixColors(MessagesImpl.KICK_BROADCAST
					.replace("%player%", other.getName())
					.replace("%reason%", StringUtils.join(args, " ", 1, args.length))));
		
			return;
		}
	}

}
