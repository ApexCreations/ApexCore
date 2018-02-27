package me.savvy.main.listeners;

import me.savvy.api.listeners.ApexListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent extends ApexListener implements Listener {

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    this.addToCache(player.getUniqueId());

    if (this.getAPI().getApexConfigCache().isSpawnSet() &&
        this.getAPI().getApexConfigCache().shouldTeleportToSpawnOnJoin()) {
      player.teleport(this.getAPI().getApexConfigCache().getSpawnLocation());
    }
  }
}