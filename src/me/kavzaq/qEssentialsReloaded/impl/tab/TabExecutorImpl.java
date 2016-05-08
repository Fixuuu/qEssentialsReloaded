package me.kavzaq.qEssentialsReloaded.impl.tab;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.interfaces.tab.TabExecutor;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class TabExecutorImpl implements TabExecutor {
	
	// inspirowane systemem Karolka, kappa.

	public String[][] tabslots = new String[20][4];
	private static final GameProfile[][] gameprofiles = new GameProfile[20][4];
	
	public void loadTab() {
		for (int rows = 0; rows < 20; rows++) {
			for (int columns = 0; columns < 4; columns++) {
				String d = "#invalid.ver#" + columns + rows;
				gameprofiles[rows][columns] = new GameProfile(UUID.randomUUID(), d);
			}
		}
	}
	
	@Override
	public void executeTab(Player player) {
		for (int columns = 0; columns < 4; columns++) {
			for (int rows = 0; rows < 20; rows++) {
				String slot = tabslots[rows][columns];
				GameProfile gp = gameprofiles[rows][columns];
				Main.getTabManager().sendPacket(player, gp, slot, "ADD_PLAYER");
			}
		}
	}

	@Override
	public void addSlot(Player player, int row, int column, String content) {
		tabslots[row][column] = Util.fixColors(content);
	}

	@Override
	public void updateSlot(Player player, int row, int column, String content) {
		tabslots[row][column] = Util.fixColors(content);
		Main.getTabManager().sendPacket(player, gameprofiles[row][column], tabslots[row][column], "UPDATE_DISPLAY_NAME");
	}

	@Override
	public void clearTab(Player player) {
		for (int columns = 0; columns < 4; columns++) {
			for (int rows = 0; rows < 20; rows++) {
				this.tabslots[rows][columns] = "";
			}
		}
	}

	@Override
	public Property getProperty(String nickname) {
		return null;
		
	}
	
	

}
