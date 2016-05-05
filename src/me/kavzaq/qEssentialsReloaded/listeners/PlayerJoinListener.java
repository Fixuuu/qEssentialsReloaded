package me.kavzaq.qEssentialsReloaded.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.interfaces.User;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class PlayerJoinListener implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		User u = Main.getUserManager().getUser(p);
		
		String joinMessage = Main.getInstance().getConfig().getString("join-format");
		joinMessage = joinMessage.replace("{PLAYER}", e.getPlayer().getName());
		joinMessage = joinMessage.replace("{DISPLAYNAME}", e.getPlayer().getDisplayName());
		e.setJoinMessage(Util.fixColors(joinMessage));
		
		if (u == null) {
			u = Main.getUserManager().implementUser(p);
		}
		
		u.setGod(false);
		
		Main.getTabExecutor().clearTab(p);
		Main.getTabManager().sendPacketHeaderFooter(p, "&2Testujemy plugin!", "&a1.0.0R");
		Main.getTabExecutor().addSlot(p, 7, 2, "&2Cuming soon!");
		Main.getTabExecutor().addSlot(p, 13, 3, "&a:D");
		Main.getTabExecutor().executeTab(p);
	}
}
