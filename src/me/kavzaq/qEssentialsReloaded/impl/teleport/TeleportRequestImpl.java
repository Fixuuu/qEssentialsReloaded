package me.kavzaq.qEssentialsReloaded.impl.teleport;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import me.kavzaq.qEssentialsReloaded.interfaces.teleport.TeleportRequest;

public class TeleportRequestImpl implements TeleportRequest{
	
	private static HashMap<Player, Player> lastReceiverByRequester = Maps.newHashMap();
	private static HashMap<Player, Player> lastRequesterByReceiver = Maps.newHashMap();
	private static HashMap<Player, Long> lastRequest = Maps.newHashMap();

	@Override
	public HashMap<Player, Player> getRequesterByReceiver() {
		return lastRequesterByReceiver;
	}

	@Override
	public HashMap<Player, Player> getReceiverByRequester() {
		return lastReceiverByRequester;
	}

	@Override
	public HashMap<Player, Long> getLastRequest() {
		return lastRequest;
	}

	@Override
	public void sendRequest(Player requester, Player receiver) {
		lastReceiverByRequester.put(requester, receiver);
		lastRequesterByReceiver.put(receiver, requester);
		lastRequest.put(requester, Long.valueOf(System.currentTimeMillis()));
	}

	@Override
	public void removeRequest(Player requester, Player receiver) {
		lastReceiverByRequester.remove(requester);
		lastRequesterByReceiver.remove(receiver);
		lastRequest.remove(requester);
		
	}

	@Override
	public boolean alreadyRequested(Player requester, Player receiver) {
		return lastRequesterByReceiver.get(receiver) == requester;
	}

	@Override
	public boolean hasRequestPending(Player receiver) {
		return lastRequesterByReceiver.get(receiver) != null;
	}

}
