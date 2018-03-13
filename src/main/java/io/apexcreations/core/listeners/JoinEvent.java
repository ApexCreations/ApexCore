package io.apexcreations.core.listeners;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.players.impl.ApexPlayerImpl;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private ApexCore apexCore;

    public JoinEvent(ApexCore apexCore) {
        this.apexCore = apexCore;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!this.apexCore.getPlayerCache().has(player.getUniqueId())) {
            this.apexCore.getPlayerCache()
                    .add(player.getUniqueId(), new ApexPlayerImpl(apexCore, player.getUniqueId()));
        }

        if (this.apexCore.getApexConfigCache().isSpawnSet() &&
                this.apexCore.getApexConfigCache().shouldTeleportToSpawnOnJoin()) {
            player.teleport(this.apexCore.getApexConfigCache().getSpawnLocation());
        }
    }
}