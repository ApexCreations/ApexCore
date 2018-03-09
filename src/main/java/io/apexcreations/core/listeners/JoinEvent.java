package io.apexcreations.core.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent extends ApexListener implements Listener {

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    this.addToCache(player.getUniqueId());

    if (this.getPlugin().getApexConfigCache().isSpawnSet() &&
        this.getPlugin().getApexConfigCache().shouldTeleportToSpawnOnJoin()) {
      player.teleport(this.getPlugin().getApexConfigCache().getSpawnLocation());
    }
  }
}