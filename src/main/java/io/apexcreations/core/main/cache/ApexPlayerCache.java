package io.apexcreations.core.main.cache;

import io.apexcreations.core.api.players.ApexPlayer;
import io.apexcreations.core.main.players.ApexPlayerImpl;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.bukkit.entity.Player;

public class ApexPlayerCache {

  private final Map<UUID, ApexPlayer> apexPlayers;

  public ApexPlayerCache() {
    this.apexPlayers = new HashMap<>();
  }

  public Optional<ApexPlayer> get(Player player) {
    return this.get(player.getUniqueId());
  }

  public Optional<ApexPlayer> get(UUID uuid) {
    if (!this.has(uuid)) {
      return Optional.empty();
    }
    return Optional.of(this.apexPlayers.get(uuid));
  }

  public boolean has(UUID uuid) {
    return this.apexPlayers.containsKey(uuid);
  }

  public void add(ApexPlayer apexPlayer) {
    if (this.has(apexPlayer.getUniqueId())) {
      return;
    }
    this.apexPlayers.put(apexPlayer.getUniqueId(), apexPlayer);
  }

  public void add(UUID uuid) {
    if (this.has(uuid)) {
      return;
    }
    ApexPlayer apexPlayer = new ApexPlayerImpl(uuid);
    add(apexPlayer);
  }

  public void remove(UUID uuid) {
    if (!this.has(uuid)) {
      return;
    }
    this.apexPlayers.remove(uuid);
  }

  public Map<UUID, ApexPlayer> getApexPlayers() {
    return Collections.unmodifiableMap(this.apexPlayers);
  }
}
