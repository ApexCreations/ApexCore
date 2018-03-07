package io.apexcreations.core.api.listeners;

import me.savvy.ApexAPI;
import me.savvy.ApexCore;
import me.savvy.api.cache.ApexMapCache;
import me.savvy.api.players.ApexPlayer;
import me.savvy.main.cache.ApexPlayerCache;

import java.util.UUID;
import me.savvy.main.players.ApexPlayerImpl;

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
