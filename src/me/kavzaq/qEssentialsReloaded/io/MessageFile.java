package me.kavzaq.qEssentialsReloaded.io;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.kavzaq.qEssentialsReloaded.Main;

public class MessageFile {
	
	private static File dataFolder = Main.getInstance().getDataFolder();
	private static File messageFile = new File(dataFolder, "messages_" 
				+ Main.getInstance().getConfig().getString("language") + ".yml");
	
	public static File getFile()
	{
		return messageFile;
	}

	public static void loadFile()
	{
		if(!dataFolder.exists())
		{
			dataFolder.mkdirs();
		}
		else if(!messageFile.exists())
		{
			try{
				messageFile.createNewFile();
				return;
			}catch(IOException ex) {
				ex.printStackTrace();
			}
		}
		
	}

	public static FileConfiguration getFileConfiguration()
	{
		return YamlConfiguration.loadConfiguration(messageFile);
	}
	
}
