package me.kavzaq.qEssentialsReloaded.io;

import java.io.IOException;
import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class Messages {
	
	public static void saveMessages() {
		Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {
				FileConfiguration data = MessageFile.getFileConfiguration();
				for(Field fld : MessagesImpl.class.getFields()) {
					if(!data.isSet(fld.getName())) {
						try {
							data.set(fld.getName(), fld.get(fld.getName()));
						} catch (IllegalArgumentException | IllegalAccessException ex) {
							ex.printStackTrace();
						}
					}
				}
				
				try {
					data.save(MessageFile.getFile());
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}

		});
	}
	
	public static void loadMessages()
	{
		Bukkit.getScheduler().runTask(Main.getInstance(), new Runnable() {

			@Override
			public void run() {
				try{
					final FileConfiguration data = MessageFile.getFileConfiguration();
					for(final Field fld : MessagesImpl.class.getFields())
					{
						if(data.isSet(fld.getName())) {
							if(Util.isFieldList(fld)) fld.set(null, 
									data.getStringList(fld.getName().replace("\\n", "\n")));
							else{
								fld.set(null, data.get(fld.getName()));
							}
						}
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				
			}
			
		});
	
	
	}
}
