package me.savvy.api.listeners;

import me.savvy.ApexCore;
import me.savvy.main.cache.ApexPlayerCache;

import java.util.UUID;

public abstract class ApexListener {

  public ApexCore getPlugin() {
    return ApexCore.getInstance();
  }

  protected ApexPlayerCache getPlayerCache() {
    return this.getPlugin().getApexAPI().getPlayerCache();
  }
  
  protected void removeFromCache(UUID uuid) {
      this.getPlayerCache().remove(uuid);
  }
  
  protected void addToCache(UUID uuid) {
      this.getPlayerCache().add(uuid);
  }
}
