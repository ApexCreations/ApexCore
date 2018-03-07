package io.apexcreations.core;

import io.apexcreations.core.api.cache.ApexMapCache;
import io.apexcreations.core.api.cache.ApexSetCache;
import io.apexcreations.core.api.database.DatabaseAdapter;
import io.apexcreations.core.api.modules.Module;
import io.apexcreations.core.api.players.ApexPlayer;
import io.apexcreations.core.main.cache.ApexConfigCache;
import io.apexcreations.core.main.cache.SubCommandCache;

import java.util.UUID;


public class ApexAPI {

  private final ApexCore apexCore;
  private final ApexMapCache<UUID, ApexPlayer> apexPlayerCache;
  private final ApexMapCache<String, Module> apexModuleCache;
 //private final ApexPlayerCache apexPlayerCache;
  private final SubCommandCache subCommandCache;
 // private final ApexModuleCache apexModuleCache;
  private final ApexConfigCache apexConfigCache;
  private DatabaseAdapter databaseAdapter;

  public ApexAPI() {
    this.apexCore = ApexCore.getInstance();
 //   this.apexPlayerCache = new ApexPlayerCache();
   // this.apexModuleCache = new ApexModuleCache();
    this.subCommandCache = new SubCommandCache();
    this.apexConfigCache = new ApexConfigCache(this.apexCore);
    this.apexPlayerCache = new ApexMapCache<>(true);
    this.apexModuleCache = new ApexMapCache<>(true);
    this.handleDatabase();
  }

  public ApexMapCache<UUID, ApexPlayer> getPlayerCache() {
    return this.apexPlayerCache;
  }

  public SubCommandCache getSubCommandCache() {
    return this.subCommandCache;
  }

  public ApexMapCache<String, Module> getApexModuleCache() {
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
