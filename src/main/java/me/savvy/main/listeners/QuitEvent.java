package me.savvy.main.listeners;

import me.savvy.api.listeners.ApexListener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent extends ApexListener implements Listener {

  @EventHandler
  public void onQuit(PlayerQuitEvent event) {
    Player player = event.getPlayer();
    this.removeFromCache(player.getUniqueId());
  }
}
