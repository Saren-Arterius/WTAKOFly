package net.wtako.WTAKOFly.EventHandlers;

import net.wtako.WTAKOFly.Events.PlayerWFlyStartEvent;
import net.wtako.WTAKOFly.Events.PlayerWFlyTickEvent;
import net.wtako.WTAKOFly.Utils.Config;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.massivecraft.factions.FFlag;
import com.massivecraft.factions.entity.BoardColls;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.UPlayer;
import com.massivecraft.mcore.ps.PS;

public class FactionListener implements Listener {

    @EventHandler
    public void onBuildFactionMemberStart(PlayerWFlyStartEvent event) {
        final UPlayer uplayer = UPlayer.get(event.getPlayer());
        if (uplayer.hasFaction() && FactionListener.isBuildFaction(uplayer.getFaction())) {
            event.setExpRequired(event.getExpRequired() * Config.BUILD_FACTION_COST_FACTOR.getDouble());
            event.setExpCost(event.getExpCost() * Config.BUILD_FACTION_COST_FACTOR.getDouble());
        }
    }

    @EventHandler
    public void onBuildFactionMemberUse(PlayerWFlyTickEvent event) {
        final UPlayer uplayer = UPlayer.get(event.getPlayer());
        if (FactionListener.isPlayerInsideOwnFaction(uplayer) && FactionListener.isBuildFaction(uplayer.getFaction())) {
            event.setExpRequired(event.getExpRequired() * Config.BUILD_FACTION_COST_FACTOR.getDouble());
        }
    }

    public static boolean isPlayerInsideOwnFaction(UPlayer player) {
        if (!player.hasFaction()) {
            return false;
        }
        return player.getFaction() == BoardColls.get().getFactionAt(PS.valueOf(player.getPlayer().getLocation()));
    }

    public static boolean isBuildFaction(Faction faction) {
        return faction.getFlag(FFlag.PEACEFUL) && !faction.getFlag(FFlag.EXPLOSIONS);
    }

}
