package io.apexcreations.core.listeners;

import io.apexcreations.core.ApexCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    private ApexCore apexCore;

    public QuitEvent(ApexCore apexCore) {
        this.apexCore = apexCore;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.apexCore.getPlayerCache().remove(player.getUniqueId());
    }
}
