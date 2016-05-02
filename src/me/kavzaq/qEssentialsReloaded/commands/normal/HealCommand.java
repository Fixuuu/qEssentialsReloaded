package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class HealCommand extends CommandImpl{

	public HealCommand() {
		super("heal", "Heals player", "/heal [player]", "heal", Arrays.asList("ulecz", "qheal"));
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		if (args.length >= 2) {
			Util.sendMessage(sender, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player p = (Player)sender;
				p.setHealth(20.0);
				p.setFoodLevel(20);
				Util.sendMessage(p, MessagesImpl.HEAL_SUCCESS);
				return;
			} else{
				Util.sendMessage(sender, MessagesImpl.ONLY_PLAYER);
				return;
			}
		} 
		else if (args.length == 1) {
			if(sender.hasPermission("qessentials.heal.others")){
				if (Bukkit.getPlayer(args[0]) == null) {
					Util.sendMessage(sender, MessagesImpl.OFFLINE_PLAYER);
					return;
				}
				Player other = Bukkit.getPlayer(args[0]);
				if (sender.equals(other)) {
					Util.sendMessage(sender, MessagesImpl.SAME_PERSON);
					return;
				}
				other.setHealth(20.0);
				other.setFoodLevel(20);
				Util.sendMessage(sender, MessagesImpl.HEAL_OTHER_SUCCESS
						.replace("%player%", other.getName()));
				Util.sendMessage(other, MessagesImpl.HEAL_OTHER_HEALED
						.replace("%player%", sender.getName()));
				return;
			}else{
				Util.sendMessage(sender, MessagesImpl.NO_PERMISSION.replace("%permission%", "qessentials.heal.others"));
			}
		}
		
	}
	
	

}
