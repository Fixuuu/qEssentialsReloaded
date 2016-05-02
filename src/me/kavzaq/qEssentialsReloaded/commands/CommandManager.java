package me.kavzaq.qEssentialsReloaded.commands;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.SimplePluginManager;

public class CommandManager {
	
	public static CommandMap getCommandMap() {
		Field field = null;
		try {
			field = SimplePluginManager.class.getDeclaredField("commandMap");
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		field.setAccessible(true);
		try {
			return (CommandMap)field.get(Bukkit.getServer().getPluginManager());
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean registerCommand(Command command) {
		getCommandMap().register("qessentials", command);
		return false;
	}

}
