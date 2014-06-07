package net.wtako.WTAKOFly.Commands.WFly;

import net.wtako.WTAKOFly.Main;
import net.wtako.WTAKOFly.Utils.Lang;

import org.bukkit.command.CommandSender;

public class ArgHelp {

    public ArgHelp(CommandSender sender) {
        sender.sendMessage(Main.getInstance().getName() + " v" + Main.getInstance().getProperty("version"));
        sender.sendMessage("Author: " + Main.getInstance().getProperty("author"));
        sender.sendMessage(Lang.HELP_RELOAD.toString());
    }

}
