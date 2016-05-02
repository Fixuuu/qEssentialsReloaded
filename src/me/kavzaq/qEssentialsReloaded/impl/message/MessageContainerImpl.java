package me.kavzaq.qEssentialsReloaded.impl.message;

import java.util.HashMap;

import org.bukkit.entity.Player;

import com.google.common.collect.Maps;

import me.kavzaq.qEssentialsReloaded.interfaces.message.MessageContainer;

public class MessageContainerImpl implements MessageContainer {

	private static final HashMap<Player, Player> messageContainer = Maps.newHashMap();
	
	@Override
	public HashMap<Player, Player> getMessageContainer() {
		return messageContainer;
	}

}
