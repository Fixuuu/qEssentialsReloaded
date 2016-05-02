package me.kavzaq.qEssentialsReloaded.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class PlayerMoveListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOW)
	public void onMove(PlayerMoveEvent e){
		Player p = e.getPlayer();
		Location nfrom = e.getFrom();
		Location nto = e.getTo();
		
		if(nfrom == null || nto == null) return;
		if(nfrom.getBlockX() == nto.getBlockX() && nfrom.getBlockZ() == nto.getBlockZ()) return;
		
		if(TeleportUtils.users.contains(p)){
			TeleportUtils.users.remove(p);
			Util.sendMessage(p, MessagesImpl.TELEPORT_DENY);
		}
		
	}

}
