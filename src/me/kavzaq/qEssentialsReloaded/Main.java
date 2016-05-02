package me.kavzaq.qEssentialsReloaded;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.Metrics;

import me.kavzaq.qEssentialsReloaded.commands.CommandManager;
import me.kavzaq.qEssentialsReloaded.commands.aliases.NightAlias;
import me.kavzaq.qEssentialsReloaded.commands.aliases.SunAlias;
import me.kavzaq.qEssentialsReloaded.commands.aliases.SunnyAlias;
import me.kavzaq.qEssentialsReloaded.commands.aliases.ThunderAlias;
import me.kavzaq.qEssentialsReloaded.commands.normal.BackCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.BroadcastCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ChatCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ClearInventoryCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.DelHomeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.EnchantCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.FeedCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.FlyCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.GameModeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.GiveCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.GodCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.HealCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.HelpCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.HomeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ItemCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.KitCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ListCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.MessageCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.ReplyCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.SetHomeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.SetSpawnCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.SpawnCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TimeCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpAcceptCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpDenyCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.TpaCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.WeatherCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.WhoIsCommand;
import me.kavzaq.qEssentialsReloaded.commands.normal.WorldCommand;
import me.kavzaq.qEssentialsReloaded.commands.qessentials.QEssentialsDeveloper;
import me.kavzaq.qEssentialsReloaded.database.SQLite;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.KitManagerImpl;
import me.kavzaq.qEssentialsReloaded.impl.managers.UserManagerImpl;
import me.kavzaq.qEssentialsReloaded.impl.message.MessageContainerImpl;
import me.kavzaq.qEssentialsReloaded.impl.tab.TabExecutorImpl;
import me.kavzaq.qEssentialsReloaded.impl.tab.TabManagerImpl;
import me.kavzaq.qEssentialsReloaded.impl.teleport.TeleportRequestImpl;
import me.kavzaq.qEssentialsReloaded.impl.teleport.TeleportUpdaterImpl;
import me.kavzaq.qEssentialsReloaded.interfaces.User;
import me.kavzaq.qEssentialsReloaded.io.MessageFile;
import me.kavzaq.qEssentialsReloaded.io.Messages;
import me.kavzaq.qEssentialsReloaded.listeners.AsyncPlayerChatListener;
import me.kavzaq.qEssentialsReloaded.listeners.EntityDamageListener;
import me.kavzaq.qEssentialsReloaded.listeners.FoodLevelChangeListener;
import me.kavzaq.qEssentialsReloaded.listeners.PlayerJoinListener;
import me.kavzaq.qEssentialsReloaded.listeners.PlayerMoveListener;
import me.kavzaq.qEssentialsReloaded.listeners.SignChangeListener;
import me.kavzaq.qEssentialsReloaded.utils.EnchantmentUtils;
import me.kavzaq.qEssentialsReloaded.utils.PaginatorUtils;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

public class Main extends JavaPlugin{
	
	private static Main inst;
	private static UserManagerImpl userManager;
	private static MessagesImpl messages;
	private static CommandImpl command;
	private static TabManagerImpl tabmanager;
	private static TabExecutorImpl tabexecutor;
	private static TeleportRequestImpl teleportrequests;
	private static TeleportUpdaterImpl teleportupdater;
	private static MessageContainerImpl messagecontainer;
	private static KitManagerImpl kitmanager;
	private static Logger l = Bukkit.getLogger();
	
	// Vault
	public static Economy economy = null;
	public static Chat chat = null;
	public static boolean economy_support = false;
	public static boolean chat_support = false;
	
	private boolean setupChat()
	{
		RegisteredServiceProvider<Chat> chatProvider = 
				getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
		if (chatProvider != null) {
			chat = chatProvider.getProvider();
		}

		return (chat != null);
	}

	private boolean setupEconomy()
	{
		RegisteredServiceProvider<Economy> economyProvider = 
				getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}
	
	private long startTime;
	private long loadTime;
	
	public static Main getInstance() {
		return inst;
	}
	
	public static KitManagerImpl getKitManager() {
		return kitmanager;
	}
	
	public static MessageContainerImpl getMessageData() {
		return messagecontainer;
	}
	
	public static TeleportRequestImpl getTeleportRequests() {
		return teleportrequests;
	}
	
	public static TabManagerImpl getTabManager() {
		return tabmanager;
	}
	
	public static TabExecutorImpl getTabExecutor() {
		return tabexecutor;
	}
	
	public static MessagesImpl getMessages() {
		return messages;
	}
	
	public static CommandImpl getCommandManager() {
		return command;
	}
	
	public static UserManagerImpl getUserManager() {
		return userManager;
	}
	
	public static TeleportUpdaterImpl getTeleportUpdater() {
		return teleportupdater;
	}
	
	public static void Debug(String string) {
		l.info(string);
	}
	
	public void onLoad() {
		l.info("[qEssentialsReloaded] [Preload] Instantiating java plugin...");
		inst = this;
		l.info("[qEssentialsReloaded] [Preload] Creating variables, connecting to SQLite and creating tables...");
		new SQLite();
		l.info("[qEssentialsReloaded] [Preload] Instantiating object implementations...");
		userManager = new UserManagerImpl();
		messages = new MessagesImpl();
		tabmanager = new TabManagerImpl();
		tabexecutor = new TabExecutorImpl();
		teleportupdater = new TeleportUpdaterImpl();
		teleportrequests = new TeleportRequestImpl();
		messagecontainer = new MessageContainerImpl();
		kitmanager = new KitManagerImpl();
	}
	
	public void onDisable() {
		for (User user : getUserManager().getUsers()) {
			user.save();
		}
	}
	
	public void onEnable() {
		startTime = System.currentTimeMillis();
		l.info("[qEssentialsReloaded] Loading resources...");
		l.info("[qEssentialsReloaded] Doing some miscellaneous work...");
		l.info("[qEssentialsReloaded] [Misc] Loading Vault optionally...");
		Plugin vault = Bukkit.getPluginManager().getPlugin("Vault");
		if ((!vault.isEnabled() || (vault == null))) {
			l.info("[qEssentialsReloaded] [Misc] Vault missing, disabling chat and economy functions...");
		}
		else {
			l.info("[qEssentialsReloaded] [Misc] Vault found, enabling chat and economy functions...");
			setupChat();
			setupEconomy();
			economy_support = true;
			chat_support = true;
		}
		l.info("[qEssentialsReloaded] Generating config and message files...");
		MessageFile.loadFile();
		Messages.loadMessages();
		Messages.saveMessages();
		saveDefaultConfig();
		l.info("[qEssentialsReloaded] Registering listeners...");
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new PlayerJoinListener(), this);
		pm.registerEvents(new PlayerMoveListener(), this);
		pm.registerEvents(new AsyncPlayerChatListener(), this);
		pm.registerEvents(new EntityDamageListener(), this);
		pm.registerEvents(new FoodLevelChangeListener(), this);
		pm.registerEvents(new SignChangeListener(), this);
		l.info("[qEssentialsReloaded] Registering commands...");
		CommandManager.registerCommand(new QEssentialsDeveloper());
		CommandManager.registerCommand(new GameModeCommand());
		CommandManager.registerCommand(new BroadcastCommand());
		CommandManager.registerCommand(new HealCommand());
		CommandManager.registerCommand(new ItemCommand());
		CommandManager.registerCommand(new ClearInventoryCommand());
		CommandManager.registerCommand(new TpaCommand());
		CommandManager.registerCommand(new TpAcceptCommand());
		CommandManager.registerCommand(new TpDenyCommand());
		CommandManager.registerCommand(new FeedCommand());
		CommandManager.registerCommand(new SetSpawnCommand());
		CommandManager.registerCommand(new FlyCommand());
		CommandManager.registerCommand(new BackCommand());
		CommandManager.registerCommand(new SpawnCommand());
		CommandManager.registerCommand(new TpCommand());
		CommandManager.registerCommand(new ChatCommand());
		CommandManager.registerCommand(new WeatherCommand());
		CommandManager.registerCommand(new TimeCommand());
		CommandManager.registerCommand(new ListCommand());
		CommandManager.registerCommand(new WorldCommand());
		CommandManager.registerCommand(new HomeCommand());
		CommandManager.registerCommand(new DelHomeCommand());
		CommandManager.registerCommand(new SetHomeCommand());
		CommandManager.registerCommand(new WhoIsCommand());
		CommandManager.registerCommand(new MessageCommand());
		CommandManager.registerCommand(new ReplyCommand());
		CommandManager.registerCommand(new GodCommand());
		CommandManager.registerCommand(new HelpCommand());
		CommandManager.registerCommand(new KitCommand());
		CommandManager.registerCommand(new GiveCommand());
		CommandManager.registerCommand(new EnchantCommand());
		//aliases
		CommandManager.registerCommand(new SunnyAlias());
		CommandManager.registerCommand(new ThunderAlias());
		CommandManager.registerCommand(new SunAlias());
		CommandManager.registerCommand(new NightAlias());
		l.info("[qEssentialsReloaded] Preloading tab...");
		Main.getTabExecutor().loadTab();
		l.info("[qEssentialsReloaded] Configuring help paged map...");
		PaginatorUtils.configureHelp();
		l.info("[qEssentialsReloaded] Configuring enchantment aliases...");
		EnchantmentUtils.configureEnchantments();
		l.info("[qEssentialsReloaded] Loading kits...");
		Main.getKitManager().load();
		loadTime = System.currentTimeMillis() - startTime;
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
			l.info("[qEssentialsReloaded] [Metrics] Successfully instantiated metrics!");
		} catch (IOException e) {
			l.info("[qEssentialsReloaded] [Metrics] Failed to instantiate the metrics!");
		}
		l.info("[qEssentialsReloaded] Completed successfuly! (" + (loadTime) + "ms)");
	
	}
}
