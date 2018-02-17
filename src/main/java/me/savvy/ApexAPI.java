package me.savvy;

import me.savvy.main.cache.ApexPlayerCache;
import me.savvy.main.cache.SubCommandCache;

public class ApexAPI {

  private ApexPlayerCache apexPlayerCache;
  private SubCommandCache subCommandCache;

  public ApexAPI() {
    this.apexPlayerCache = new ApexPlayerCache();
    this.subCommandCache = new SubCommandCache();
  }

  public ApexPlayerCache getPlayerCache() {
    return this.apexPlayerCache;
  }

  public SubCommandCache getSubCommandCache() {
    return this.subCommandCache;
  }
}
