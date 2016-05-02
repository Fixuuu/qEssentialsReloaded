package me.kavzaq.qEssentialsReloaded.interfaces.teleport;

import java.util.HashMap;

import org.bukkit.entity.Player;

public interface TeleportRequest {
	
	// mozna by to w sumie lepiej zrobic, np. obiektami, hashmapy w sumie nie sa najlepszym rozwiazaniem
	// ale jestem leniem, elo
	
	HashMap<Player, Player> getRequesterByReceiver();
	HashMap<Player, Player> getReceiverByRequester();
	HashMap<Player, Long> getLastRequest();
	
	void sendRequest(Player requester, Player receiver);
	void removeRequest(Player requester, Player receiver);
	
	boolean alreadyRequested(Player requester, Player receiver);
	boolean hasRequestPending(Player receiver);

}
