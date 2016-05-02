package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.ParsingUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class GiveCommand extends CommandImpl {

	public GiveCommand() {
		super("give", "Gives a item to player", "/give <player> <material:data> <amount> [name] [loreline1;loreline2] [enchantment:1;enchantment:2]", "give", 
				Arrays.asList("qgive", "daj"));
		
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		if (args.length <= 2) {
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		Player other = Bukkit.getPlayer(args[0]);
		if(other == null){
			Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
			return;
		}
		String syntax = StringUtils.join(args, " ", 1, args.length);
		ItemStack item;
		try {
			item = ParsingUtils.getParsedItem(syntax);
		}catch (Exception e) {
			Util.sendMessage(s, MessagesImpl.ITEM_UNKNOWN);
			return;
		}
		HashMap<Integer, ItemStack> itemsNotStored = other.getInventory().addItem(item);
		for(Entry<Integer, ItemStack> entry : itemsNotStored.entrySet())
		{   
		    other.getWorld().dropItemNaturally(other.getLocation(), entry.getValue());
		}
		Util.sendMessage(other, MessagesImpl.ITEM_OTHER_GIVE
				.replace("%player%", s.getName())
				.replace("%amount%", String.valueOf(item.getAmount()))
				.replace("%item%", item.getType().toString().toLowerCase().replace("_", " ")));
		Util.sendMessage(s, MessagesImpl.ITEM_OTHER_SUCCESS
				.replace("%player%", other.getName())
				.replace("%amount%", String.valueOf(item.getAmount()))
				.replace("%item%", item.getType().toString().toLowerCase().replace("_", " ")));
	}

}
