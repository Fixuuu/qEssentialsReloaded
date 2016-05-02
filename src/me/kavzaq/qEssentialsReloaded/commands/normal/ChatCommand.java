package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;
import me.kavzaq.qEssentialsReloaded.utils.switches.ChatSwitch;

public class ChatCommand extends CommandImpl {

	public ChatCommand() {
		super("chat", "Chat management", "/chat [clear]", "chat", Arrays.asList("qchat", "czat"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		if(args.length >= 2){
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 0){
			boolean chatState = ChatSwitch.switchChat();
			if (chatState) {
				Bukkit.broadcastMessage(Util.fixColors(MessagesImpl.CHAT_ENABLED
						.replace("%player%", s.getName())));
				return;
			}
			else {
				Bukkit.broadcastMessage(Util.fixColors(MessagesImpl.CHAT_DISABLED
						.replace("%player%", s.getName())));
				return;
			}
		}
		else if (args.length == 1) {
			if (!args[0].equalsIgnoreCase("clear")){
				Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
				return;
			}
			for (int i = 0; i < 100; i++) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					p.sendMessage(" ");
				}
			}
			Bukkit.broadcastMessage(Util.fixColors(MessagesImpl.CHAT_CLEARED
					.replace("%player%", s.getName())));
		}
		
	}
	
	

}
