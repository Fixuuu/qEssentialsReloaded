package me.kavzaq.qEssentialsReloaded.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import me.kavzaq.qEssentialsReloaded.utils.Util;

public class SignChangeListener implements Listener {
	
	@EventHandler
	public void onSignChange(SignChangeEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("qessentials.sign.color")) {
			for (int i = 0; i <= 3; i++) {
				e.setLine(i, Util.fixColors(e.getLine(i)));
			}
		}
	}

}
