package me.kavzaq.qEssentialsReloaded.interfaces.tab;

import org.bukkit.entity.Player;

import com.mojang.authlib.properties.Property;

public interface TabExecutor {
	
	void executeTab(Player player);
	void addSlot(Player player, int row, int column, String content);
	void updateSlot(Player player, int row, int column, String content);
	Property getProperty(String nickname);
	void clearTab(Player player);
	

}
