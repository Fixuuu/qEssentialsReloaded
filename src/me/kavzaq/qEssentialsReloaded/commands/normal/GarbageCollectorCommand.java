package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.lang.management.ManagementFactory;
import java.util.Arrays;

import org.bukkit.command.CommandSender;

import me.kavzaq.qEssentialsReloaded.enums.AverageTime;
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
		String tps = String.valueOf(TPSMonitor.getCurrentTPS()).contains("-1.0") ? "Calculating..." : 
			String.valueOf(TPSMonitor.getCurrentTPS()); 
		
		String percentage = String.valueOf(TPSMonitor.getPercentage()).contains("105.0") ? "0%" : 
			String.valueOf(TPSMonitor.getPercentage()) + "%";
		
		double _averageTPS1 = Util.round(TPSMonitor.getAverageTPS(AverageTime.ONE_MIN), 3);
		double _averageTPS2 = Util.round(TPSMonitor.getAverageTPS(AverageTime.FIVE_MIN), 3);
		double _averageTPS3 = Util.round(TPSMonitor.getAverageTPS(AverageTime.FIFTEEN_MIN), 3);
		
		String averageTPS1 = String.valueOf(_averageTPS1).contains("-1") ? "Calculating..." : 
			String.valueOf(_averageTPS1);
		String averageTPS2 = String.valueOf(_averageTPS2).contains("-1") ? "Calculating..." : 
			String.valueOf(_averageTPS2);
		String averageTPS3 = String.valueOf(_averageTPS3).contains("-1") ? "Calculating..." : 
			String.valueOf(_averageTPS3);
		
		String averagePerc1 = String.valueOf(TPSMonitor.getPercentage(_averageTPS1)).contains("105.0") ? "0%" : 
			String.valueOf(TPSMonitor.getPercentage(_averageTPS1)) + "%";
		String averagePerc2 = String.valueOf(TPSMonitor.getPercentage(_averageTPS2)).contains("105.0") ? "0%" : 
			String.valueOf(TPSMonitor.getPercentage(_averageTPS2)) + "%";
		String averagePerc3 = String.valueOf(TPSMonitor.getPercentage(_averageTPS3)).contains("105.0") ? "0%" : 
			String.valueOf(TPSMonitor.getPercentage(_averageTPS3)) + "%";
		
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
					.replace("%os%", ManagementFactory.getOperatingSystemMXBean().getName() + ", v" + ManagementFactory.getOperatingSystemMXBean().getVersion())
					.replace("%java%", System.getProperty("java.version"))
					
					.replace("%1mAvgTPS%", averageTPS1)
					.replace("%5mAvgTPS%", averageTPS2)
					.replace("%15mAvgTPS%", averageTPS3)
					
					.replace("%1mAvgPercentage%", averagePerc1)
					.replace("%5mAvgPercentage%", averagePerc2)
					.replace("%15mAvgPercentage%", averagePerc3));
		}
		
	}
	
	

}
