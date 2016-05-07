package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.lang.management.ManagementFactory;
import java.util.Arrays;

import org.bukkit.command.CommandSender;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.runnables.tpsmonitor.TPSMonitor;
import me.kavzaq.qEssentialsReloaded.utils.ListingUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class GarbageCollectorCommand extends CommandImpl {

	public GarbageCollectorCommand() {
		super("garbagecollector", "Server machine/bukkit world informations", "/garbagecollector", "garbagecollector", Arrays.asList("gc", "qgc", "garbagec"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		String tps = String.valueOf(TPSMonitor.getCurrentTPS()).contains("-1.0") ? "Preloading..." : 
			String.valueOf(TPSMonitor.getCurrentTPS()); 
		
		String percentage = String.valueOf(TPSMonitor.getPercentage()).contains("105.0") ? "0.000" : 
			String.valueOf(TPSMonitor.getPercentage()) + "%";
		
		for (String str : MessagesImpl.GARBAGECOLLECTOR_INFO) {
			Util.sendMessage(s, str
					.replace("%maxMemory%", String.valueOf(Runtime.getRuntime().maxMemory() / 1024L / 1024L) + " MB")
					.replace("%totalMemory%", String.valueOf(Runtime.getRuntime().totalMemory() / 1024L / 1024L) + " MB")
					.replace("%freeMemory%", String.valueOf(Runtime.getRuntime().freeMemory() / 1024L / 1024L) + " MB")
					.replace("%worlds%", ListingUtils.getWorldList())
					.replace("%tps%", tps)
					.replace("%percentage%", percentage)
					.replace("%cores%", String.valueOf(Runtime.getRuntime().availableProcessors()))
					.replace("%uptime%", Util.parseTime(ManagementFactory.getRuntimeMXBean().getUptime()))
					.replace("%os%", ManagementFactory.getOperatingSystemMXBean().getName() + ", v" + 
					ManagementFactory.getOperatingSystemMXBean().getVersion())
					.replace("%java%", System.getProperty("java.version")));
		}
		
	}
	
	

}
