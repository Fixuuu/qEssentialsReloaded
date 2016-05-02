package me.kavzaq.qEssentialsReloaded.commands.normal;

import java.util.Arrays;

import org.bukkit.command.CommandSender;

import me.kavzaq.qEssentialsReloaded.Main;
import me.kavzaq.qEssentialsReloaded.impl.CommandImpl;
import me.kavzaq.qEssentialsReloaded.impl.MessagesImpl;
import me.kavzaq.qEssentialsReloaded.utils.PaginatorUtils;
import me.kavzaq.qEssentialsReloaded.utils.Util;

public class HelpCommand extends CommandImpl {

	public HelpCommand() {
		super("help", "Shows a command help", "/help [page]", "help", Arrays.asList("cmdlist", "qhelp"));
	}

	@Override
	public void onExecute(CommandSender s, String[] args) {
		int _pageLength = Main.getInstance().getConfig().getInt("help-page-length");
		int page;
		if (args.length == 0) {
			page = 1;
		}
		else {
			try {
				page = Integer.valueOf(args[0]);
				if (page == 0)
					page = 1;
			}catch (NumberFormatException e) { page = 1; }
		}
		if (!PaginatorUtils.containsPage(page, _pageLength)) {
			Util.sendMessage(s, MessagesImpl.HELP_UNKNOWN_PAGE);
			return;
		}
		PaginatorUtils.paginateHelp(s, page, _pageLength);
	}

}
