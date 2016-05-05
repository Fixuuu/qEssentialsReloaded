package me.kavzaq.qEssentialsReloaded.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		String quitMessage = Main.getInstance().getConfig().getString("quit-format");
		quitMessage = quitMessage.replace("{PLAYER}", e.getPlayer().getName());
		quitMessage = quitMessage.replace("{DISPLAYNAME}", e.getPlayer().getDisplayName());
		e.setQuitMessage(Util.fixColors(quitMessage));
	}

}
