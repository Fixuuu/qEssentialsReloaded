package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class HeadCommand extends CommandImpl {

	public HeadCommand() {
		super("head", "Gives a player head", "/head [player]", "head", Arrays.asList("dajmiglowe", "qhead"), true);
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		Player p = (Player)s;
		ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
		SkullMeta skull = (SkullMeta)head.getItemMeta();
		if (args.length == 0) {
			skull.setDisplayName(Util.fixColors(MessagesImpl.HEAD_NAME
					.replace("%player%", p.getName())));
			skull.setOwner(p.getName());
		}
		else if (args.length == 1) {
			skull.setDisplayName(Util.fixColors(MessagesImpl.HEAD_NAME
					.replace("%player%", args[0])));
			skull.setOwner(args[0]);
		}
		else {
			Util.sendMessage(p, MessagesImpl.BAD_ARGS + getUsage());
			return;
		}
		head.setItemMeta(skull);
		p.getInventory().addItem(head);
	}

}
