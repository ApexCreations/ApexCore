package me.savvy.api.listeners;

import me.savvy.ApexCore;
import me.savvy.main.cache.ApexPlayerCache;

public abstract class ApexListener {

  public ApexCore getPlugin() {
    return ApexCore.getInstance();
  }

  protected ApexPlayerCache getPlayerCache() {
    return ApexPlayerCache.getInstance();
  }
}
