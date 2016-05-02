package me.kavzaq.qEssentialsReloaded.commands.qessentials;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.database.SQLite;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.interfaces.User;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class QEssentialsDeveloper extends CommandImpl {

	public QEssentialsDeveloper() {
		super("qdev", "Special information for developers", "/qdev", "qdev", Arrays.asList("qdeveloper", "qdebug"));
	}

	@Override
	public void onExecute(CommandSender sender, String[] args) {
		// gathering data
		Player p = (Player)sender;
		User u = Main.getUserManager().getUser(p);
		
		Util.sendMessage(p, "Version: " + Main.getInstance().getDescription().getVersion());
		Util.sendMessage(p, "User object information:");
		Util.sendMessage(p, "   Name: " + u.getName());
		Util.sendMessage(p, "   UUID: " + u.getUUID());
		Util.sendMessage(p, "   is God: " + u.isGod());
		Util.sendMessage(p, "SQLite information:");
		Util.sendMessage(p, "   Driver: " + SQLite.driver);
		Util.sendMessage(p, "   Database URL: " + SQLite.database_url);
		return;
	}

	

}
