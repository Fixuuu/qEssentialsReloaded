package me.kavzaq.qEssentialsReloaded.listeners;

import org.apache.commons.lang3.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.SlowdownUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.switches.ChatSwitch;

public class AsyncPlayerChatListener implements Listener {
	
	@EventHandler(priority = EventPriority.LOW)
	public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		if (!ChatSwitch.getChat()) {
			if (!p.hasPermission("qessentials.chat.bypass")) {
				e.setCancelled(true);
				Util.sendMessage(p, MessagesImpl.CHAT_IS_DISABLED);
				return;
			}
		}
		
		String format = Main.getInstance().getConfig().getString("chat-format");
		format = StringUtils.replace(format, "{PLAYER}", p.getName());
		format = StringUtils.replace(format, "{DISPLAYNAME}", p.getDisplayName());
		format = StringUtils.replace(format, "{HEALTH}", String.valueOf(p.getHealth()/2));
		format = StringUtils.replace(format, "{FOOD}", String.valueOf(p.getFoodLevel()/2));
		format = StringUtils.replace(format, "{MESSAGE}", e.getMessage());
		e.setFormat(Util.fixColors(format));
		
		int _slowdownTime = Main.getInstance().getConfig().getInt("chat-slowdown");
		int _slowdownTimeMS = _slowdownTime * 1000;
		
		if (SlowdownUtils.isBlocked(p)) {
			e.setCancelled(true);
			Util.sendMessage(p, MessagesImpl.CHAT_SLOWDOWN.replace("%remain%", SlowdownUtils.timeRemain(p)));
			return;
		}
		
		if (p.hasPermission("qessentials.chat.slowdown.bypass")) {
			SlowdownUtils.setLastMessage(p, System.currentTimeMillis() + _slowdownTimeMS);
		}
	}

}
