package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.TeleportUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class TpAcceptCommand extends CommandImpl {

	public TpAcceptCommand() {
		super("tpaccept", "Accepts a teleport request", "/tpaccept", "tpaccept", Arrays.asList("tpyes", "qtpaccept", "akceptuj"), true);
		
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
			Util.sendMessage(requester, MessagesImpl.TPACCEPT_OTHER_ACCEPTED.replace("%player%", p.getName()));
			Util.sendMessage(p, MessagesImpl.TPACCEPT_ACCEPTED.replace("%player%", requester.getName()));
			TeleportUtils tpu = new TeleportUtils(requester);
			tpu.teleport(p.getLocation());
			Util.sendMessage(requester, MessagesImpl.TELEPORT_SUCCESS);
			return;
		}else{
			Util.sendMessage(p, MessagesImpl.TELEPORT_NO_REQUEST);
			return;
		}
	}

}
