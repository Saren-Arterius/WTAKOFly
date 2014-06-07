package net.wtako.WTAKOFly.Commands;

import net.wtako.WTAKOFly.Commands.WFly.ArgHelp;
import net.wtako.WTAKOFly.Commands.WFly.ArgReload;
import net.wtako.WTAKOFly.Commands.WFly.ArgWFly;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandWFly implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length >= 1) {
            if (args[0].equalsIgnoreCase("help") || args[0].equalsIgnoreCase("?")) {
                new ArgHelp(sender);
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                new ArgReload(sender);
                return true;
            }
        } else {
            new ArgWFly(sender);
            return true;
        }
        return false;
    }
}
