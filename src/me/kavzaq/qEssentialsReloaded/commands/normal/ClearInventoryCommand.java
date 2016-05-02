package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class ClearInventoryCommand extends CommandImpl {

	public ClearInventoryCommand() {
		super("clear", "Clears player inventory", "/clear [player]", "clear",
				Arrays.asList("cl", "clearinv", "clearinventory", "wyczysc", "qclear"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		if (args.length >= 2) {
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 0) {
			if (!(s instanceof Player)) {
				Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
				return;
			}
			Player p = (Player)s;
			PlayerInventory i = p.getInventory();
			i.clear();
			i.setArmorContents(new ItemStack[i.getArmorContents().length]);
			i.setExtraContents(new ItemStack[i.getExtraContents().length]);
			i.setStorageContents(new ItemStack[i.getStorageContents().length]);
			
			i.setHeldItemSlot(0);
			Util.sendMessage(p, MessagesImpl.CLEAR_SUCCESS);
		}
		else if (args.length == 1) {
			if(s.hasPermission("qessentials.clear.others")){
				if (Bukkit.getPlayer(args[0]) == null) {
					Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
					return;
				}
				Player other = Bukkit.getPlayer(args[0]);
				if (s.equals(other)) {
					Util.sendMessage(s, MessagesImpl.SAME_PERSON);
					return;
				}
				PlayerInventory i = other.getInventory();
				
				i.clear();
				i.setArmorContents(new ItemStack[i.getArmorContents().length]);
				i.setExtraContents(new ItemStack[i.getExtraContents().length]);
				i.setStorageContents(new ItemStack[i.getStorageContents().length]);
				
				i.setHeldItemSlot(0);
				
				Util.sendMessage(s, MessagesImpl.CLEAR_OTHER_SUCCESS
						.replace("%player%", other.getName()));
				Util.sendMessage(other, MessagesImpl.CLEAR_OTHER_CLEARED
						.replace("%player%", s.getName()));
				return;
			}else{
				Util.sendMessage(s, MessagesImpl.NO_PERMISSION.replace("%permission%", "qessentials.clear.others"));
			}
		}
	}

}
