package me.savvy;

import me.savvy.main.cache.ApexPlayerCache;

public abstract class ApexListener {

  public ApexCore getPlugin() {
    return ApexCore.getInstance();
  }

  protected ApexPlayerCache getPlayerCache() {
    return ApexPlayerCache.getInstance();
  }
}
