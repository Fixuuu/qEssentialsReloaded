package me.kavzaq.qEssentialsReloaded.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import me.kavzaq.qEssentialsReloaded.Main;

public class SQLite {

	private File file = new File(Main.getInstance().getDataFolder(), "sqlite.db");
	
	public static String driver;
	public static String database_url;
	
	public SQLite() {
		// setting variables
		driver = "org.sqlite.JDBC";
		if (!Main.getInstance().getDataFolder().exists()) {
			Main.getInstance().getDataFolder().mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		database_url = "jdbc:sqlite:" + file.getAbsolutePath();
		
		// searching for driver
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		try {
			createTables();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void executeUpdate(PreparedStatement stat) {
		Bukkit.getScheduler().runTaskAsynchronously(Main.getInstance(), new Runnable() {

			@Override
			public void run() {
				try {
					Connection conn = createConnection();
					stat.executeUpdate();
					stat.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		});
	}
	 
	public static Connection createConnection() {
		// connecting
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(database_url);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return conn;
	}
	
	private static void createTables() throws SQLException {
		String queryUsers = 
				"CREATE TABLE IF NOT EXISTS users ("
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name VARCHAR(20) NOT NULL, "
				+ "uuid VARCHAR(36) NOT NULL, "
				+ "homes VARCHAR(320),"
				+ "kits VARCHAR(320))";
		
		PreparedStatement stat = createConnection().prepareStatement(queryUsers);			
		executeUpdate(stat);
	}

}
