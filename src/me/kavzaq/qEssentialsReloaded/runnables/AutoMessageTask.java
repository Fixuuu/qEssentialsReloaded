package me.kavzaq.qEssentialsReloaded.runnables;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class AutoMessageTask implements Runnable {
	
	private static int currentIndex = 0;

	private static final List<String> messageList = 
			Main.getInstance().getConfig().getStringList("automessages");
	
	@Override
	public void run() {
		if ((messageList.size() == 0) || (messageList == null)) {
			return;
		}
		if (currentIndex > (messageList.size() - 1)) {
			currentIndex = 0;
		}
		String currentMessage = messageList.get(currentIndex);	currentIndex++;
		for (Player p : Bukkit.getOnlinePlayers()) {
			Util.sendMessage(p, currentMessage);
		}
		return;
	}

}
