package io.apexcreations.core.api.listeners;


import io.apexcreations.core.ApexAPI;
import io.apexcreations.core.ApexCore;
import io.apexcreations.core.api.cache.ApexMapCache;
import io.apexcreations.core.api.players.ApexPlayer;
import io.apexcreations.core.main.players.ApexPlayerImpl;
import java.util.UUID;

public abstract class ApexListener {

  public ApexCore getPlugin() {
    return ApexCore.getInstance();
  }

  protected ApexAPI getAPI() {
    return this.getPlugin().getApexAPI();
  }

  protected ApexMapCache<UUID, ApexPlayer> getPlayerCache() {
    return this.getAPI().getPlayerCache();
  }

  protected void removeFromCache(UUID uuid) {
    this.getPlayerCache().remove(uuid);
  }

  protected void addToCache(UUID uuid) {
    this.getPlayerCache().add(uuid, new ApexPlayerImpl(uuid));
  }
}
