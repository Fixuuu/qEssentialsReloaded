package me.kavzaq.qEssentialsReloaded.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.interfaces.User;

public class PlayerJoinListener implements Listener{

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		User u = Main.getUserManager().getUser(p);
		
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
