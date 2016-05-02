package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.ParsingUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class ItemCommand extends CommandImpl {

	public ItemCommand() {
		super("item", "Gives an item", "/item <material:data> <amount> [name] [loreline1;loreline2] [enchantment:1;enchantment:2]", "item", Arrays.asList("i", "przedmiot", "qitem"), true);
		
	}

	private static final StringBuilder localsb = new StringBuilder();
	
	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if((args.length == 0)) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		localsb.setLength(0);
		for (String str : args) {
			if (localsb.length() > 0) {
				localsb.append(" ");
			}
			localsb.append(str);
		}
		ItemStack item;
		try {
			item = ParsingUtils.getParsedItem(localsb.toString());
		}catch (Exception e) {
			Util.sendMessage(p, MessagesImpl.ITEM_UNKNOWN);
			return;
		}
		HashMap<Integer, ItemStack> itemsNotStored = p.getInventory().addItem(item);
		for(Entry<Integer, ItemStack> entry : itemsNotStored.entrySet())
		{   
		    p.getWorld().dropItemNaturally(p.getLocation(), entry.getValue());
		}
		Util.sendMessage(s, MessagesImpl.ITEM_SUCCESS
				.replace("%amount%", String.valueOf(item.getAmount()))
				.replace("%item%", item.getType().toString().toLowerCase().replace("_", " ")));
		return;
		
	}

}
