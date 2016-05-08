package me.kavzaq.qEssentialsReloaded.runnables;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.utils.TablistUtils;

public class TablistRefreshTask implements Runnable {

	@Override
	public void run() {
		try {
			for (Player p : Bukkit.getOnlinePlayers()) {
				TablistUtils.updateTab(p);
			}
		} catch (Exception e) {
			// najlepsza metoda na pozbycie sie bledu jest...
			
			// nie wyswietlanie go!
			
			// a tak na serio, blad ten nie wplywa kompletnie na serwer
			// bo sekunde pozniej wszystko sie juz laduje.
			// mozliwe ze user nie byl zaktualizowany i wywalalo nulla
			// jak chcialem go pobrac.
		}
	}

}
