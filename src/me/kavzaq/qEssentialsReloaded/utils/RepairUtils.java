package me.kavzaq.qEssentialsReloaded.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;

public class RepairUtils {
	
	private RepairUtils() { }
	private static final Material[] repairable = { 
			Material.DIAMOND_PICKAXE, Material.DIAMOND_SWORD, Material.DIAMOND_SPADE, Material.DIAMOND_AXE, Material.DIAMOND_HOE, 
			Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.IRON_PICKAXE, 
			Material.IRON_SWORD, Material.IRON_SPADE, Material.IRON_AXE, Material.IRON_HOE, Material.IRON_HELMET, Material.IRON_CHESTPLATE, 
			Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.GOLD_PICKAXE, Material.GOLD_SWORD, Material.GOLD_SPADE, Material.GOLD_AXE, 
			Material.GOLD_HOE, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.STONE_PICKAXE, 
			Material.STONE_SWORD, Material.STONE_SPADE, Material.STONE_AXE, Material.STONE_HOE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, 
			Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS, Material.WOOD_PICKAXE, Material.WOOD_SWORD, Material.WOOD_SPADE, 
			Material.WOOD_AXE, Material.WOOD_HOE, Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, 
			Material.LEATHER_BOOTS, Material.FLINT_AND_STEEL, Material.SHEARS, Material.BOW, Material.FISHING_ROD, Material.ANVIL };
	
	public static Material[] getRepairableItems() {
		return repairable;
	}
	
	public static boolean containsRepairable(Material[] repairable, Material material) {
		for (Material mat : repairable) {
			if (material.equals(mat)) {
				return true;
			}
		}
		return false;
	}
	
	public static void repair(Player player) {
		ItemStack item = player.getInventory().getItemInMainHand();
		if(item != null && containsRepairable(getRepairableItems(), item.getType())) {
			item.setDurability((short) 0);
			Util.sendMessage(player, MessagesImpl.REPAIR_SUCCESS);
			return;
		}
		Util.sendMessage(player, MessagesImpl.REPAIR_UNKNOWN);
	}
	
	public static void repairArmor(Player player) {
		for(ItemStack i : player.getInventory().getArmorContents()) {
			if(i != null && containsRepairable(getRepairableItems(), i.getType())) {
				i.setDurability((short) 0);
			}
		}
		Util.sendMessage(player, MessagesImpl.REPAIR_ARMOR_SUCCESS);
	}
	
	public static void repairAll(Player player) {
		repairArmor(player);
		for(ItemStack i : player.getInventory().getContents()) {
			if(i != null && containsRepairable(getRepairableItems(), i.getType())) {
				i.setDurability((short) 0);
			}
		}
		Util.sendMessage(player, MessagesImpl.REPAIR_ALL_SUCCESS);
	}

}
