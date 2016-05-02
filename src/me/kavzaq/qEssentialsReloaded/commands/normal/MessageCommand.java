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

public class MessageCommand extends CommandImpl {

	public MessageCommand() {
		super("message", "Sends a message to player", "/message <player>", "message", Arrays.asList("qmessage", "msg", "m"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		// @TODO console support
		Player p = (Player)s;
		if (args.length < 2) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		Player other = Bukkit.getPlayer(args[0]);
		if (other == null) {
			Util.sendMessage(p, MessagesImpl.OFFLINE_PLAYER);
			return;
		}
		String message = Util.fixColors(StringUtils.join(args, " ", 1, args.length));
		Main.getMessageData().getMessageContainer().put(p, other);
		Main.getMessageData().getMessageContainer().put(other, p);
		
		Util.sendMessage(p, MessagesImpl.MESSAGE_TO_FORMAT
				.replace("%player%", other.getName())
				.replace("%message%", message));
		Util.sendMessage(other, MessagesImpl.MESSAGE_FROM_FORMAT
				.replace("%player%", p.getName())
				.replace("%message%", message));
	}

}
