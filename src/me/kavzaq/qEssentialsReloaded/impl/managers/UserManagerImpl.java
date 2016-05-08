package me.kavzaq.qEssentialsReloaded.impl.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.database.SQLite;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.interfaces.managers.UserManager;
import me.kavzaq.qEssentialsReloaded.utils.SerializeUtils;

public class UserManagerImpl implements UserManager {

	private final List<UserImpl> users = Lists.newArrayList();
	
	public List<UserImpl> getUsers() {
		return users;
	}
	
	public UserManagerImpl() {
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new Runnable() {
			@Override
			public void run() {
				Connection conn = SQLite.createConnection();
				Statement stat;
				try {
					stat = conn.createStatement();
					String query = "SELECT * FROM users";
					
					ResultSet rs = stat.executeQuery(query);
					while (rs.next()) {
						UserImpl user = new UserImpl(rs.getString("name"), 
								UUID.fromString(rs.getString("uuid")));
						user.setGod(false);
						user.setHomes(SerializeUtils.deserializeList(rs.getString("homes")));
						user.setKits(SerializeUtils.deserializeList(rs.getString("kits")));
						users.add(user);
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
				
			}
		});
	}
	
	@Override
	public UserImpl implementUser(Player player) {
		UserImpl user = new UserImpl(player);
		
		user.setGod(false);
		user.setHomes(Lists.newArrayList());
		user.setKits(Lists.newArrayList());
		
		PreparedStatement stat = null;
		try {
			stat = SQLite.createConnection().prepareStatement(
					"INSERT INTO `users` (`id`, `name`, `uuid`, `homes`, `kits`) VALUES (NULL, '?', '?', '?', '?')");
			stat.setString(1, user.getName());
			stat.setString(2, user.getUUID().toString());
			stat.setString(3, SerializeUtils.serializeList(user.getHomes()));
			stat.setString(4, SerializeUtils.serializeList(user.getKits()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		SQLite.executeUpdate(stat);
		users.add(user);
		return user;
	}
	
	@Override
	public UserImpl getUser(Player player) {
		for (UserImpl user : users) {
			if (user.getUUID().equals(player.getUniqueId())) return user;
		}
		return null;
	}

	@Override
	public UserImpl getUser(CommandSender sender) {
		return null;
	}

	@Override
	public UserImpl getUser(UUID uuid) {
		return null;
	}


}
