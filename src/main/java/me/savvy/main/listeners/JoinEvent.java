package me.savvy.main.listeners;

import me.savvy.api.listeners.ApexListener;
import me.savvy.api.players.ApexPlayer;
import me.savvy.main.players.ApexPlayerImpl;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent extends ApexListener implements Listener {

  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    ApexPlayer apexPlayer = new ApexPlayerImpl(event.getPlayer().getUniqueId());
    Bukkit.broadcastMessage(event.getPlayer().getName() + " just got BAMMED!");
    this.getPlayerCache().add(apexPlayer);
  }
}