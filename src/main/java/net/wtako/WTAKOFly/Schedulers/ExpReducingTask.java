package net.wtako.WTAKOFly.Schedulers;

import java.util.ArrayList;

import me.desht.dhutils.ExperienceManager;
import net.wtako.WTAKOFly.Main;
import net.wtako.WTAKOFly.Methods.FlyManager;

import org.bukkit.entity.Player;

public class ExpReducingTask {

    private static ExpReducingTask   instance;
    private static ArrayList<Player> flyOffPlayers = new ArrayList<Player>();

    public ExpReducingTask() {
        Main.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                reduceExpOrFlyOff();
            }
        }, 0L, 1L);
        ExpReducingTask.instance = this;
    }

    private void reduceExpOrFlyOff() {
        for (final Player flyingPlayer: FlyManager.getAllFlyingPlayers()) {
            final ExperienceManager manager = new ExperienceManager(flyingPlayer);
            if (!manager.hasExp(Main.getInstance().getConfig().getDouble("Flying.EXPCostPerSecond") / 20D)) {
                ExpReducingTask.flyOffPlayers.add(flyingPlayer);
            } else {
                manager.changeExp(-Main.getInstance().getConfig().getDouble("Flying.EXPCostPerSecond") / 20D);
            }
        }
        for (final Player flyOffPlayer: ExpReducingTask.flyOffPlayers) {
            FlyManager.setNotFly(flyOffPlayer);
        }
        ExpReducingTask.flyOffPlayers.clear();
    }

    public static ExpReducingTask getInstance() {
        return ExpReducingTask.instance;
    }

}