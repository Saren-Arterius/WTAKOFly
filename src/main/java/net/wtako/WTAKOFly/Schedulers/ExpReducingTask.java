package net.wtako.WTAKOFly.Schedulers;

import java.util.ArrayList;

import net.wtako.WTAKOFly.Main;
import net.wtako.WTAKOFly.Events.PlayerWFlyTickEvent;
import net.wtako.WTAKOFly.Methods.FlyManager;
import net.wtako.WTAKOFly.Utils.Config;
import net.wtako.WTAKOFly.Utils.ExperienceManager;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ExpReducingTask extends BukkitRunnable {

    private static ExpReducingTask instance;

    public ExpReducingTask() {
        ExpReducingTask.instance = this;
        runTaskTimer(Main.getInstance(), 0L, Config.TICK_INTERVAL.getLong());
    }

    @Override
    public void run() {
        for (final Player flyingPlayer: new ArrayList<Player>(FlyManager.getAllFlyingPlayers())) {
            final ExperienceManager manager = FlyManager.getExpManager(flyingPlayer);
            final double expRequired = Config.EXP_COST_PER_SECOND.getDouble() / (20D / Config.TICK_INTERVAL.getLong());
            final PlayerWFlyTickEvent event = new PlayerWFlyTickEvent(flyingPlayer, expRequired);
            Main.getInstance().getServer().getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                FlyManager.setNotFly(flyingPlayer);
                continue;
            }
            if (!manager.hasExp(event.getExpRequired())) {
                FlyManager.setNotFly(flyingPlayer);
            } else {
                manager.changeExp(-event.getExpRequired());
            }
        }
    }

    public static ExpReducingTask getInstance() {
        return ExpReducingTask.instance;
    }

}