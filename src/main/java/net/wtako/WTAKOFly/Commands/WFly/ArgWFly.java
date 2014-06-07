package net.wtako.WTAKOFly.Commands.WFly;

import net.wtako.WTAKOFly.Methods.FlyManager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ArgWFly {

    public ArgWFly(CommandSender sender) {
        FlyManager.toggleFly((Player) sender);
    }

}
