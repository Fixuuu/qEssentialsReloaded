package me.kavzaq.qEssentialsReloaded.runnables.metrics;

import org.mcstats.Metrics.Graph;
import org.mcstats.Metrics.Plotter;

import me.kavzaq.qEssentialsReloaded.Main;

public class MetricsCollector implements Runnable {

	@Override
	public void run() {
		Graph g = Main.getMetrics().createGraph("qEssentials Graph");
		g.addPlotter(new Plotter("Users") {

			@Override
			public int getValue() {
				return Main.getUserManager().getUsers().size();
			}
			
		});
		Main.getMetrics().start();
	}

}
