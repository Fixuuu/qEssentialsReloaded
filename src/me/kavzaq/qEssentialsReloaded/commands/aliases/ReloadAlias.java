package me.kavzaq.qEssentialsReloaded.commands.aliases;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;

public class ReloadAlias extends CommandImpl {

	public ReloadAlias() {
		super("reload", "Reloads a server", "/reload", "reload.aliases", Arrays.asList("rl", "rlconfirm", "qrl", "qreload", "przeladuj", "rlc"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload confirm");
	}

}
