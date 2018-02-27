package me.savvy;

import me.savvy.api.database.DatabaseAdapter;
import me.savvy.main.cache.ApexConfigCache;
import me.savvy.main.cache.ApexModuleCache;
import me.savvy.main.cache.ApexPlayerCache;
import me.savvy.main.cache.SubCommandCache;

public class ApexAPI {

  private final ApexCore apexCore;
  private final ApexPlayerCache apexPlayerCache;
  private final SubCommandCache subCommandCache;
  private final ApexModuleCache apexModuleCache;
  private final ApexConfigCache apexConfigCache;
  private DatabaseAdapter databaseAdapter;

  public ApexAPI() {
    this.apexCore = ApexCore.getInstance();
    this.apexPlayerCache = new ApexPlayerCache();
    this.apexModuleCache = new ApexModuleCache();
    this.subCommandCache = new SubCommandCache();
    this.apexConfigCache = new ApexConfigCache(this.apexCore);
    this.handleDatabase();
  }

  public ApexPlayerCache getPlayerCache() {
    return this.apexPlayerCache;
  }

  public SubCommandCache getSubCommandCache() {
    return this.subCommandCache;
  }

  public ApexModuleCache getApexModuleCache() {
    return this.apexModuleCache;
  }

  public DatabaseAdapter getDatabaseAdapter() {
    return databaseAdapter;
  }

  public ApexConfigCache getApexConfigCache() {
    return this.apexConfigCache;
  }

  private void handleDatabase() {
    if (!this.apexCore.getConfig().getBoolean("mysql.enabled")) {
      return;
    }
    this.databaseAdapter = new DatabaseAdapter(
        this.apexCore.getConfig().getString("mysql.hostName"),
        this.apexCore.getConfig().getInt("mysql.port"),
        this.apexCore.getConfig().getString("mysql.userName"),
        this.apexCore.getConfig().getString("mysql.password"),
        this.apexCore.getConfig().getString("mysql.databaseName"));
  }
}
