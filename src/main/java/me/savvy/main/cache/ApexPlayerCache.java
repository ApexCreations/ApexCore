package me.savvy.main.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import me.savvy.api.players.ApexPlayer;
import org.bukkit.entity.Player;

public class ApexPlayerCache {

  private static ApexPlayerCache instance;
  private Map<UUID, ApexPlayer> apexPlayers;

  public ApexPlayerCache() {
    instance = this;
    this.apexPlayers = new HashMap<>();
  }

  public Optional<ApexPlayer> get(Player player) {
    return this.get(player.getUniqueId());
  }

  public Optional<ApexPlayer> get(UUID uuid) {
    if (!this.has(uuid)) return Optional.empty();
    return Optional.of(this.apexPlayers.get(uuid));
  }

  public boolean has(UUID uuid) {
    return this.apexPlayers.containsKey(uuid);
  }

  public void add(ApexPlayer apexPlayer) {
    if (this.has(apexPlayer.getUniqueId())) return;
    this.apexPlayers.put(apexPlayer.getUniqueId(), apexPlayer);
  }

  public void remove(UUID uuid) {
    if (!this.has(uuid)) return;
    this.apexPlayers.remove(uuid);
  }

  public Map<UUID, ApexPlayer> getApexPlayers() {
    return Collections.unmodifiableMap(this.apexPlayers);
  }

  public static ApexPlayerCache getInstance() {
    return instance == null ? new ApexPlayerCache() : instance;
  }

}
