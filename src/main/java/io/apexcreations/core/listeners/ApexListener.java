package io.apexcreations.core.listeners;


import com.google.inject.Inject;
import io.apexcreations.core.ApexAPI;
import io.apexcreations.core.ApexCore;
import io.apexcreations.core.main.cache.ApexMapCache;
import io.apexcreations.core.players.ApexPlayer;
import io.apexcreations.core.players.impl.ApexPlayerImpl;
import java.util.UUID;

public abstract class ApexListener {

  @Inject
  private ApexCore apexCore;

  public ApexCore getPlugin() {
    return apexCore;
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