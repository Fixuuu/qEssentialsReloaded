package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.GameModeUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class GameModeCommand extends CommandImpl {

	public GameModeCommand() {
		super("gamemode", "Changes player gamemode", "/gamemode <mode> [player]", "gamemode", Arrays.asList("gm", "tryb", "qgm"));
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		if ((args.length == 0) || (args.length >= 3)) {
			Util.sendMessage(sender, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 1) {
			if (!(sender instanceof Player)) {
				Util.sendMessage(sender, MessagesImpl.ONLY_PLAYER);
				return;
			}
			Player p = (Player)sender;
			GameMode mode = GameModeUtils.parseGameMode(args[0]);
			if (mode == null) {
				Util.sendMessage(p, MessagesImpl.GAMEMODE_UNKNOWN);
				return;
			}
			p.setGameMode(mode);
			Util.sendMessage(p, MessagesImpl.GAMEMODE_SUCCESS.replace("%mode%", mode.name().toLowerCase()));
			return;
		}
		else if (args.length == 2) {
			if (Bukkit.getPlayer(args[1]) == null) {
				Util.sendMessage(sender, MessagesImpl.OFFLINE_PLAYER);
				return;
			}
			if (!sender.hasPermission("qessentials.gamemode.others")) {
				Util.sendMessage(sender, MessagesImpl.NO_PERMISSION.replace("%permission%", "qessentials.gamemode.others"));
				return;
			}
			Player other = Bukkit.getPlayer(args[1]);
			if (sender.equals(other)) {
				Util.sendMessage(sender, MessagesImpl.SAME_PERSON);
				return;
			}
			GameMode mode = GameModeUtils.parseGameMode(args[0]);
			if (mode == null) {
				Util.sendMessage(sender, MessagesImpl.GAMEMODE_UNKNOWN);
				return;
			}
			other.setGameMode(mode);
			Util.sendMessage(sender, MessagesImpl.GAMEMODE_OTHER_SUCCESS
					.replace("%mode%", mode.name().toLowerCase())
					.replace("%player%", other.getName()));
			Util.sendMessage(other, MessagesImpl.GAMEMODE_OTHER_CHANGED
					.replace("%mode%", mode.name().toLowerCase())
					.replace("%player%", sender.getName()));
		}
		return;
	}

}
