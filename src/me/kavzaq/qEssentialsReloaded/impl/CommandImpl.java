package me.kavzaq.qEssentialsReloaded.impl;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.utils.Util;

public abstract class CommandImpl extends Command {
	
	private String permission;
	private boolean isPlayerOnly;
	
	public CommandImpl(String name, String description, String usage, String permission, List<String> aliases) {
		super(name, description, usage, aliases);
		this.permission = "qessentials." + permission;
	}
	
	public CommandImpl(String name, String description, String usage, String permission, List<String> aliases, boolean isPlayerOnly) {
		super(name, description, usage, aliases);
		this.permission = "qessentials." + permission;
		this.isPlayerOnly = isPlayerOnly;
	}

	public abstract void onExecute(CommandSender s, String[] args);
	
	@Override
	public boolean execute(CommandSender s, String str, String[] args) {
		if (!s.hasPermission(this.permission)) {
			Util.sendMessage(s, MessagesImpl.NO_PERMISSION.replace("%permission%", this.permission));
			return true;
		}
		if ((this.isPlayerOnly) && (!(s instanceof Player))) {
			Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
			return true;
		}
		// tutaj bez try/catcha bo potrzebuje w jednej z komend nie wyswietlac exception jezeli taki nastapi.
		// tj. Item
		// w sumie nie wiem jak inaczej to rozwiazac. 
		onExecute(s, args);
		return true;
	}

}
