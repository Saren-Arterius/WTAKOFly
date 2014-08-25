package net.wtako.WTAKOFly.Methods;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

import net.wtako.WTAKOFly.Main;
import net.wtako.WTAKOFly.Events.PlayerWFlyEndEvent;
import net.wtako.WTAKOFly.Events.PlayerWFlyStartEvent;
import net.wtako.WTAKOFly.Utils.Config;
import net.wtako.WTAKOFly.Utils.ExperienceManager;
import net.wtako.WTAKOFly.Utils.Lang;

import org.bukkit.entity.Player;

public class FlyManager {

    private static ArrayList<Player>                  flyingPlayers = new ArrayList<Player>();
    private static HashMap<Player, ExperienceManager> expManagers   = new HashMap<Player, ExperienceManager>();

    public static ArrayList<Player> getAllFlyingPlayers() {
        return FlyManager.flyingPlayers;
    }

    public static boolean setFly(Player player) {
        if (FlyManager.flyingPlayers.contains(player)) {
            return false;
        }

        boolean allowed = false;
        for (final String worldName: Config.ALLOWED_WORLDS.getStrings()) {
            if (worldName.equalsIgnoreCase(player.getWorld().getName())) {
                if (Config.REVERSED_ALLOWED_WORLDS.getBoolean()) {
                    player.sendMessage(Lang.CANNOT_FLY_IN_THIS_WORLD.toString());
                    return false;
                }
                allowed = true;
                break;
            }
        }
        if (!allowed) {
            player.sendMessage(Lang.CANNOT_FLY_IN_THIS_WORLD.toString());
            return false;
        }

        final PlayerWFlyStartEvent event = new PlayerWFlyStartEvent(player, Config.FLY_START_MIN_EXP.getDouble(),
                Config.FLY_START_COST.getDouble());
        Main.getInstance().getServer().getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            player.sendMessage(Lang.CANNOT_START_FLYING.toString());
            return false;
        }
        final ExperienceManager manager = FlyManager.getExpManager(player);
        if (!manager.hasExp(event.getExpRequired())) {
            player.sendMessage(MessageFormat.format(Lang.NOT_ENOUGH_EXP.toString(), event.getExpRequired()));
            return false;
        }
        manager.changeExp(-event.getExpCost());
        player.sendMessage(Lang.FLY_ON.toString());
        player.setAllowFlight(true);
        FlyManager.flyingPlayers.add(player);
        return true;
    }

    public static boolean setNotFly(Player player) {
        if (!FlyManager.flyingPlayers.contains(player)) {
            return false;
        }
        player.setAllowFlight(false);
        player.sendMessage(Lang.FLY_OFF.toString());
        FlyManager.flyingPlayers.remove(player);
        final PlayerWFlyEndEvent event = new PlayerWFlyEndEvent(player);
        Main.getInstance().getServer().getPluginManager().callEvent(event);
        return true;
    }

    public static void toggleFly(Player player) {
        if (!FlyManager.setNotFly(player)) {
            FlyManager.setFly(player);
        }
    }

    public static ExperienceManager getExpManager(Player player) {
        ExperienceManager manager = FlyManager.expManagers.get(player);
        if (manager == null) {
            manager = new ExperienceManager(player);
            FlyManager.expManagers.put(player, manager);
        }
        return manager;
    }

}
