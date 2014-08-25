package net.wtako.WTAKOFly.Commands.WFly;

import net.wtako.WTAKOFly.Methods.FlyManager;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WFly {

    public WFly(CommandSender sender, String[] args) {
        FlyManager.toggleFly((Player) sender);
    }

}
