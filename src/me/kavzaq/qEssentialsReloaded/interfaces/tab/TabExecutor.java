package me.kavzaq.qEssentialsReloaded.interfaces.tab;

import org.bukkit.entity.Player;

public interface TabExecutor {
	
	void executeTab(Player player);
	void addSlot(Player player, int row, int column, String content);
	void updateSlot(Player player, int row, int column, String content);
	void addSkin(Player player, String texture);
	void clearTab(Player player);
	

}
