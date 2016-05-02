package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.EnchantmentUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class EnchantCommand extends CommandImpl {

	public EnchantCommand() {
		super("enchant", "Enchants an item", "/enchant <enchantment> [power]", "enchant", Arrays.asList("qenchant", "zaklnij"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if ((args.length >= 3) || (args.length == 0)) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}		
		ItemStack is = p.getInventory().getItemInMainHand();
		if ((is == null) || (is.getType() == Material.AIR)) {
			Util.sendMessage(p, MessagesImpl.ENCHANT_NULL_ITEM);
			return;
		}
		Enchantment ench = EnchantmentUtils.enchants.get(args[0]);
		if (ench == null) {
			Util.sendMessage(p, MessagesImpl.ENCHANT_UNKNOWN);
			return;
		}
		int power = 1;
		if (args.length == 1) {
			is.addUnsafeEnchantment(ench, power);
		}
		else if (args.length == 2) {
			try {
				power = Integer.valueOf(args[1]);
			}catch (NumberFormatException e) { power = 1; }
			is.addUnsafeEnchantment(ench, power);
		}
		p.updateInventory();
		Util.sendMessage(p, MessagesImpl.ENCHANT_SUCCESS
				.replace("%enchantment%", args[0])
				.replace("%power%", String.valueOf(power)));
	}

}
