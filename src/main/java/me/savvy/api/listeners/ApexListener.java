package me.savvy.api.listeners;

import me.savvy.ApexCore;
import me.savvy.main.cache.ApexPlayerCache;

import java.util.UUID;

public abstract class ApexListener {

  public ApexCore getPlugin() {
    return ApexCore.getInstance();
  }

  protected ApexPlayerCache getPlayerCache() {
    return ApexPlayerCache.getInstance();
  }
  
  protected void removeFromCache(UUID uuid) {
      ApexPlayerCache.getInstance().remove(uuid);
  }
  
  protected void addToCache(UUID uuid) {
      ApexPlayerCache.getInstance().add(uuid);
  }
}
