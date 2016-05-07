package me.kavzaq.qEssentialsReloaded.listeners;

import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.timed.TemporaryBanTimed;

public class AsyncPlayerPreLoginListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOW)
	public void onAsyncPlayerPreLogin(AsyncPlayerPreLoginEvent e) {
		UUID p = e.getUniqueId();
		if (TemporaryBanTimed.isBlocked(p)) {
			e.disallow(Result.KICK_BANNED, Util.fixColors(MessagesImpl.KICK_TEMPBAN
					.replace("%cooldown%", TemporaryBanTimed.timeRemain(p))));
		}
	}

}
