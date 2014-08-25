package net.wtako.WTAKOFly.Commands.WFly;

import net.wtako.WTAKOFly.Utils.CommandHelper;
import net.wtako.WTAKOFly.Utils.CommandsWFly;
import org.bukkit.command.CommandSender;

public class ArgHelp {

    public ArgHelp(CommandSender sender, String[] args) {
        CommandHelper.sendHelp(sender, CommandsWFly.values(), "");
    }

}
