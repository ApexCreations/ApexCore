package io.apexcreations.core;

import com.google.inject.Inject;
import io.apexcreations.core.database.DatabaseAdapter;
import io.apexcreations.core.main.cache.ApexConfigCache;
import io.apexcreations.core.main.cache.ApexMapCache;
import io.apexcreations.core.main.commands.SubCommand;
import io.apexcreations.core.modules.Module;
import io.apexcreations.core.players.ApexPlayer;
import java.util.UUID;


public class ApexAPI {

  private final ApexMapCache<String, SubCommand> subCommandCache;
  private final ApexMapCache<UUID, ApexPlayer> apexPlayerCache;
  private final ApexMapCache<String, Module> apexModuleCache;
  private final ApexConfigCache apexConfigCache;
  private DatabaseAdapter databaseAdapter;
  @Inject
  private ApexCore apexCore;

  ApexAPI() {
    this.apexConfigCache = new ApexConfigCache();
    this.apexPlayerCache = new ApexMapCache<>(true);
    this.apexModuleCache = new ApexMapCache<>(true);
    this.subCommandCache = new ApexMapCache<>(true);
    this.handleDatabase();
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

  public ApexMapCache<UUID, ApexPlayer> getPlayerCache() {
    return this.apexPlayerCache;
  }

  public ApexMapCache<String, SubCommand> getSubCommandCache() {
    return this.subCommandCache;
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
