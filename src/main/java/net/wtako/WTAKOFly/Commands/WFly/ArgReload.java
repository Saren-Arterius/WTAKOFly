package net.wtako.WTAKOFly.Commands.WFly;

import net.wtako.WTAKOFly.Main;
import net.wtako.WTAKOFly.Utils.Lang;

import org.bukkit.command.CommandSender;

public class ArgReload {

    public ArgReload(CommandSender sender, String[] args) {
        Main.getInstance().reloadConfig();
        Main.getInstance().getServer().getPluginManager().disablePlugin(Main.getInstance());
        Main.getInstance().getServer().getPluginManager().enablePlugin(Main.getInstance());
        sender.sendMessage(Lang.PLUGIN_RELOADED.toString());
    }

}
