package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class TpaCommand extends CommandImpl {

	public TpaCommand() {
		super("tpa", "Sends a teleport request", "/tpa <player>", "tpa", Arrays.asList("qtpa"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		if((args.length == 0) || (args.length >= 2)){
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		
		if(s.getName().equalsIgnoreCase(args[0])) {
			Util.sendMessage(p, MessagesImpl.TELEPORT_SAME_PERSON);
			return;
		}
		
		if(Bukkit.getPlayer(args[0]) == null){
			Util.sendMessage(p, MessagesImpl.OFFLINE_PLAYER);
			return;
		}
		Player receiver = Bukkit.getPlayer(args[0]);
		if (Main.getTeleportRequests().alreadyRequested(p, receiver)) {
			Util.sendMessage(p, MessagesImpl.TPA_ALREADY_REQUESTED);
			return;
		}
		
		if (Main.getTeleportRequests().hasRequestPending(receiver)) {
			Util.sendMessage(p, MessagesImpl.TPA_HAS_REQUEST_PENDING);
			return;
		}
		
		Main.getTeleportRequests().sendRequest(p, receiver);
		
		for (String str : MessagesImpl.TPA_REQUEST_GOT) {
			Util.sendMessage(receiver, str
					.replace("%player%", p.getName()));
		}
		Util.sendMessage(p, MessagesImpl.TPA_REQUEST_SENDED
				.replace("%player%", receiver.getName()));
	}

}
