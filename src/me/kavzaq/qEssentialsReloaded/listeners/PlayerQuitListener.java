package me.kavzaq.qEssentialsReloaded.listeners;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class PlayerQuitListener implements Listener {
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		String quitMessage = Main.getInstance().getConfig().getString("quit-format");
		quitMessage = StringUtils.replace(quitMessage, "{PLAYER}", e.getPlayer().getName());
		quitMessage = StringUtils.replace(quitMessage, "{DISPLAYNAME}", e.getPlayer().getDisplayName());
		e.setQuitMessage(Util.fixColors(quitMessage));
	}

}
