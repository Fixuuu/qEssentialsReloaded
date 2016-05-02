package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class FeedCommand extends CommandImpl {

	public FeedCommand() {
		super("feed", "Feeds a player", "/feed [player]", "feed", Arrays.asList("qfeed", "givemefood", "najedz"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		if(args.length >= 2){
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if(args.length == 0){
			if(!(s instanceof Player)){
				Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
				return;
			}
			Player p = (Player)s;
			p.setFoodLevel(20);
			Util.sendMessage(p, MessagesImpl.FEED_SUCCESS);
			return;
		}
		else if(args.length == 1){
			if (Bukkit.getPlayer(args[0]) == null) {
				Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
				return;
			}
			if (!s.hasPermission("qessentials.feed.others")) {
				Util.sendMessage(s, MessagesImpl.NO_PERMISSION.replace("%permission%", "qessentials.feed.others"));
				return;
			}
			Player other = Bukkit.getPlayer(args[0]);
			if (s.equals(other)) {
				Util.sendMessage(s, MessagesImpl.SAME_PERSON);
				return;
			}
			other.setFoodLevel(20);
			Util.sendMessage(s, MessagesImpl.FEED_OTHER_SUCCESS
					.replace("%player%", other.getName()));
			Util.sendMessage(other, MessagesImpl.FEED_OTHER_FEEDED
					.replace("%player%", s.getName()));
			
		}
	}
	
	

}
