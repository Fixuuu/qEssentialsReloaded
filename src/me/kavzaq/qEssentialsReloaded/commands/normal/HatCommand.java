package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class HatCommand extends CommandImpl {

	public HatCommand() {
		super("hat", "Wears a block helmet", "/hat", "hat", Arrays.asList("qhat"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if (args.length != 0) {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		PlayerInventory inv = p.getInventory();
		ItemStack hat = inv.getItemInMainHand();
		if (hat.getType().getMaxDurability() != 0) {
			Util.sendMessage(p, MessagesImpl.HAT_UNKNOWN);
			return;
		}
		inv.removeItem(hat);
		inv.setHelmet(hat);
		Util.sendMessage(p, MessagesImpl.HAT_SUCCESS);
	} 

}
