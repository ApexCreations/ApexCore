package me.savvy;

import me.savvy.main.cache.ApexModuleCache;
import me.savvy.main.cache.ApexPlayerCache;
import me.savvy.main.cache.SubCommandCache;

public class ApexAPI {

  private ApexPlayerCache apexPlayerCache = new ApexPlayerCache();
  private SubCommandCache subCommandCache = new SubCommandCache();
  private ApexModuleCache apexModuleCache = new ApexModuleCache();

  public ApexPlayerCache getPlayerCache() {
    return this.apexPlayerCache;
  }

  public SubCommandCache getSubCommandCache() {
    return this.subCommandCache;
  }

  public ApexModuleCache getApexModuleCache() {
    return this.apexModuleCache;
  }
}
