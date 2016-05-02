package me.kavzaq.qEssentialsReloaded.interfaces.tab;

import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;

public interface TabManager {
	
	void sendPacket(Player player, GameProfile gp, String slot, String mode);
	void sendPacketHeaderFooter(Player player, String header, String footer);

}
