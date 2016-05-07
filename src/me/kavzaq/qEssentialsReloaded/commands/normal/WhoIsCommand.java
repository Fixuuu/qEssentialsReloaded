package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.impl.UserImpl;
import me.kavzaq.qEssentialsReloaded.utils.BooleanUtils;
import me.kavzaq.qEssentialsReloaded.utils.ListingUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class WhoIsCommand extends CommandImpl {

	public WhoIsCommand() {
		super("whois", "Returns informations about player", "/whois [player]", "whois", Arrays.asList("qwhois", "ktoztojest"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		if (args.length >= 2) {
			Util.sendMessage(s, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		if (args.length == 0) {
			if (!(s instanceof Player)) {
				Util.sendMessage(s, MessagesImpl.ONLY_PLAYER);
				return;
			}
			Player p = (Player)s;
			UserImpl u = Main.getUserManager().getUser(p);
			for (String str : MessagesImpl.WHOIS_INFO) {
				Util.sendMessage(p, str
						.replace("%player%", p.getName())
						.replace("%uuid%", p.getUniqueId().toString())
						.replace("%addressIp%", p.getAddress().getAddress().getHostAddress())
						.replace("%isGlobalAdmin%", BooleanUtils.getOperatorParsedBoolean(p))
						.replace("%mode%", p.getGameMode().toString().toLowerCase())
						.replace("%flying%", BooleanUtils.getParsedBooleanYesNo(p.isFlying()))
						.replace("%food%", String.valueOf((p.getFoodLevel() / 2)))
						.replace("%health%", String.valueOf((p.getHealth() / 2)))
						.replace("%location%", 
								"x" + p.getLocation().getX() +
								", y" + p.getLocation().getY() +
								", z" + p.getLocation().getZ())
						.replace("%isGod%", BooleanUtils.getParsedBooleanYesNo(u.isGod()))
						.replace("%homes%", ListingUtils.getHomeList(p)));
			}
		}
		else if (args.length == 1) {
			if (Bukkit.getPlayer(args[0]) == null) {
				Util.sendMessage(s, MessagesImpl.OFFLINE_PLAYER);
				return;
			}
			Player other = Bukkit.getPlayer(args[0]);
			UserImpl otheru = Main.getUserManager().getUser(other);
			for (String str : MessagesImpl.WHOIS_INFO) {
				Util.sendMessage(s, str
						.replace("%player%", other.getName())
						.replace("%uuid%", other.getUniqueId().toString())
						.replace("%addressIp%", String.valueOf(other.getAddress().getAddress().getHostAddress()))
						.replace("%isGlobalAdmin%", BooleanUtils.getOperatorParsedBoolean(other))
						.replace("%mode%", other.getGameMode().toString().toLowerCase())
						.replace("%flying%", BooleanUtils.getParsedBooleanYesNo(other.isFlying()))
						.replace("%food%", String.valueOf((other.getFoodLevel() / 2)))
						.replace("%health%", String.valueOf((other.getHealth() / 2)))
						.replace("%location%", 
								"x" + other.getLocation().getX() +
								", y" + other.getLocation().getY() +
								", z" + other.getLocation().getZ())
						.replace("%isGod%", BooleanUtils.getParsedBooleanYesNo(otheru.isGod()))
						.replace("%homes%", ListingUtils.getHomeList(other)));
				
			}
		}
		
	}

}
