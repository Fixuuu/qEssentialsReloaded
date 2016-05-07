package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Maps;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.KitImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.interfaces.Kit;
import me.kavzaq.qEssentialsReloaded.utils.ParsingUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.timed.KitTimed;

public class KitCommand extends CommandImpl {

	public KitCommand() {
		super("kit", "Gives you a kit", "/kit <name>", "kit", Arrays.asList("qkit", "zestaw"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if (args.length >= 2)
		{
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 0) {
			Util.sendMessage(p, MessagesImpl.KIT_LIST + Main.getKitManager().getKits());
			return;
		}
		else if (args.length == 1) {
			Kit kit = null;
			for (Kit k : KitImpl.list) {
				if (k.getName().equals(args[0])) kit = k;
			}
			if (kit == null) {
				Util.sendMessage(p, MessagesImpl.KIT_UNKNOWN);
				return;
			}
			if (!p.hasPermission("qessentials.kit." + kit.getName())) {
				Util.sendMessage(p, MessagesImpl.NO_PERMISSION
						.replace("%permission%", "qessentials.kit." + kit.getName()));
				return;
			}
			if (!p.hasPermission("qessentials.kit.bypass")) {
				if (!KitTimed.canTake(kit, p)) {
					Util.sendMessage(p, MessagesImpl.KIT_COOLDOWN
							.replace("%cooldown%", KitTimed.timeRemain(kit, p)));
					return;
				}
			}
			HashMap<Integer, ItemStack> itemsNotStored = Maps.newHashMap();
			for (String item : kit.getItems()) {
				ItemStack is = null;
				try {
					is = ParsingUtils.getParsedItem(item);
				}catch (NumberFormatException e) {
					Util.sendMessage(p, "&cNumberFormatException: Item amount is not integer-friendly. Please decrement value.");
					return;
				}
				itemsNotStored = p.getInventory().addItem(is);
			}
			for (Entry<Integer, ItemStack> entry : itemsNotStored.entrySet())
			{   
			    p.getWorld().dropItemNaturally(p.getLocation(), entry.getValue());
			}
			KitTimed.takeKit(kit, p);
			Util.sendMessage(p, MessagesImpl.KIT_SUCCESS
					.replace("%name%", kit.getName()));
		}
	}

}
