package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class TpDenyCommand extends CommandImpl{
	
	public TpDenyCommand() {
		super("tpdeny", "Deny a teleport request", "/tpdeny", "tpdeny", Arrays.asList("tpno", "qtpdeny", "odmow"), true);
		
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if ((args.length == 1) || (args.length >= 2)){
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		Player requester = Main.getTeleportRequests().getRequesterByReceiver().get(p);
		if (requester == null){
			Util.sendMessage(p, MessagesImpl.TELEPORT_NO_REQUEST);
			return;
		}
		if ((System.currentTimeMillis() - ((Long)Main.getTeleportRequests().getLastRequest()
						.get(requester)).longValue()) / 1000L <= 60L) {
			
			Main.getTeleportRequests().removeRequest(requester, p);
			Util.sendMessage(requester, MessagesImpl.TPDENY_OTHER_DENIED.replace("%player%", p.getName()));
			Util.sendMessage(p, MessagesImpl.TPDENY_DENIED.replace("%player%", requester.getName()));
			return;
		}else{
			Util.sendMessage(p, MessagesImpl.TELEPORT_NO_REQUEST);
			return;
		}
	}

}
