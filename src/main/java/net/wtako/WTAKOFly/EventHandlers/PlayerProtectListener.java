package net.wtako.WTAKOFly.EventHandlers;

import java.util.ArrayList;

import net.wtako.WTAKOFly.Events.PlayerWFlyEndEvent;
import net.wtako.WTAKOFly.Methods.FlyManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerProtectListener implements Listener {

    private static ArrayList<Player> flyOffPlayers = new ArrayList<Player>();

    @EventHandler
    public void onPlayerChangeWorld(PlayerChangedWorldEvent event) {
        if (FlyManager.getAllFlyingPlayers().contains(event.getPlayer())) {
            FlyManager.setNotFly(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (FlyManager.getAllFlyingPlayers().contains(event.getPlayer())) {
            FlyManager.setNotFly(event.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerFlyOff(PlayerWFlyEndEvent event) {
        PlayerProtectListener.flyOffPlayers.add(event.getPlayer());
    }

    @EventHandler
    public void onPlayerFallDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        final Player player = (Player) event.getEntity();
        if (!PlayerProtectListener.flyOffPlayers.contains(player)) {
            return;
        }
        if (event.getCause() != DamageCause.FALL) {
            return;
        }
        PlayerProtectListener.flyOffPlayers.remove(player);
        event.setCancelled(false);
    }

}
