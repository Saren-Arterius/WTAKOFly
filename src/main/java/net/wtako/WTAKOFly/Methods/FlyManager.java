package net.wtako.WTAKOFly.Methods;

import java.util.ArrayList;

import me.desht.dhutils.ExperienceManager;
import net.wtako.WTAKOFly.Main;
import net.wtako.WTAKOFly.Utils.Lang;

import org.bukkit.entity.Player;

public class FlyManager {

    private static ArrayList<Player> flyingPlayers = new ArrayList<Player>();

    public static ArrayList<Player> getAllFlyingPlayers() {
        return FlyManager.flyingPlayers;
    }

    public static boolean setFly(Player player) {
        final ExperienceManager manager = new ExperienceManager(player);
        if (!manager.hasExp(Main.getInstance().getConfig().getDouble("Flying.MinExpToStartFlying"))) {
            player.sendMessage(Lang.NOT_ENOUGH_EXP.toString());
            return false;
        }
        if (!FlyManager.flyingPlayers.contains(player)) {
            manager.changeExp(-Main.getInstance().getConfig().getDouble("Flying.InitialEXPCost"));
            player.sendMessage(Lang.FLY_ON.toString());
            player.setAllowFlight(true);
            FlyManager.flyingPlayers.add(player);
            return true;
        }
        return false;
    }

    public static boolean setNotFly(Player player) {
        if (FlyManager.flyingPlayers.contains(player)) {
            player.setAllowFlight(false);
            player.sendMessage(Lang.FLY_OFF.toString());
            FlyManager.flyingPlayers.remove(player);
            return true;
        }
        return false;
    }

    public static void toggleFly(Player player) {
        if (!FlyManager.setNotFly(player)) {
            FlyManager.setFly(player);
        }
    }

}
