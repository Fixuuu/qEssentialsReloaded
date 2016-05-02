package me.kavzaq.qEssentialsReloaded.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.database.SQLite;
import me.kavzaq.qEssentialsReloaded.interfaces.User;
import me.kavzaq.qEssentialsReloaded.utils.SerializeUtils;

public class UserImpl implements User {

	private final String name;
	private final UUID uuid;
	private boolean god;
	private List<String> homes;
	private List<String> kits;
	
	private boolean changed = false;
	
	public UserImpl(Player player) {
		this.name = player.getName();
		this.uuid = player.getUniqueId();
	}
	
	public UserImpl(String name, UUID uuid) {
		this.name = name;
		this.uuid = uuid;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public boolean isGod() {
		return god;
	}
	
	@Override
	public List<String> getKits() {
		return kits;
	}

	@Override
	public void addKit(KitDataImpl kitData) {
		this.kits.add(SerializeUtils.serializeKit(kitData));
		changed = true;
	}

	@Override
	public void delKit(KitDataImpl kitData) {
		this.kits.remove(SerializeUtils.serializeKit(kitData));
		changed = true;
	}

	@Override
	public void setKits(List<String> kits) {
		this.kits = kits;
		changed = true;
	}
	
	@Override
	public void setGod(boolean god) {
		this.god = god;
	}

	@Override
	public List<String> getHomes() {
		return homes;
	}
	
	@Override
	public void setHomes(List<String> homes) {
		this.homes = homes;
		changed = true;
	}
	
	@Override
	public void addHome(HomeDataImpl homeData) {
		this.homes.add(SerializeUtils.serializeHome(homeData));
		changed = true;
	}
	

	@Override
	public void delHome(HomeDataImpl homeData) {
		this.homes.remove(SerializeUtils.serializeHome(homeData));
		changed = true;
	}

	@Override
	public void save() {
		if (!changed) return;
		Connection conn = SQLite.createConnection();
		Statement stat;
		try {
			stat = conn.createStatement();
			String query = String.format("UPDATE `users` SET `homes`='%s',`kits`='%s' WHERE `uuid`='%s'", 
					SerializeUtils.serializeList(this.getHomes()), SerializeUtils.serializeList(this.getKits()), this.getUUID().toString());
			stat.execute(query);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		changed = false;
	}

	public boolean isChanged() {
		return changed;
	}
	

}
