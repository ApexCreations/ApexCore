package io.apexcreations.core.main.cache;

import com.google.inject.Inject;
import io.apexcreations.core.ApexCore;
import io.apexcreations.core.main.utils.Utils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ApexConfigCache {

  @Inject
  private ApexCore apexCore;
  private final FileConfiguration config;

  private boolean teleportToSpawnOnJoin;
  private Location spawnLocation;

  private int maxBalance, minBalance, defaultBalance;

  private String currencySymbol, economyName, currencyNameSingular, currencyNamePlural;
  private boolean economyEnabled;

  public ApexConfigCache() {
    this.config = this.apexCore.getConfig();
    this.load();
  }

  private void load() {
    this.teleportToSpawnOnJoin = this.config.getBoolean("spawn.teleportOnJoin", false);
    if (this.config.isSet("spawn.location")) {
      this.spawnLocation = Utils.fromString(this.config.getString("spawn.location"));
    }
    if (this.config.isSet("economy.defaultBalance")) {
      this.defaultBalance = this.config.getInt("economy.defaultBalance", 1000);
    }
    this.economyName = this.getConfig().getString("economy.name", "Apex");
    this.economyEnabled = this.getConfig().getBoolean("economy.enabled", true);
    this.currencySymbol = this.getConfig().getString("economy.currencySymbol", "$");
    this.currencyNameSingular = this.getConfig().getString("economy.singularName", "Dollar");
    this.currencyNamePlural = this.getConfig().getString("economy.pluralName", "Dollars");
    this.minBalance = this.config.getInt("economy.minBalance", 0);
    this.maxBalance = this.config.getInt("economy.maxBalance", 10000000);
  }

  public void save() {
    this.config.set("spawn.teleportOnJoin", this.teleportToSpawnOnJoin);
    if (this.spawnLocation != null) {
      this.config.set("spawn.location", Utils.toString(this.spawnLocation));
    }
    this.config.set("economy.name", this.economyName);
    this.config.set("economy.enabled", this.economyEnabled);
    this.config.set("economy.currencySymbol", this.currencySymbol);
    this.config.set("economy.singularName", this.currencyNameSingular);
    this.config.set("economy.pluralName", this.currencyNamePlural);
    this.config.set("economy.defaultBalance", this.defaultBalance);
    this.config.set("economy.minBalance", this.minBalance);
    this.config.set("economy.maxBalance", this.maxBalance);
    this.saveConfig();
  }

  private void saveConfig() {
    this.apexCore.saveConfig();
  }

  public FileConfiguration getConfig() {
    return this.config;
  }

  public Location getSpawnLocation() {
    return this.spawnLocation;
  }

  public void setSpawnLocation(Location spawnLocation) {
    this.spawnLocation = spawnLocation;
  }

  public boolean isSpawnSet() {
    return this.spawnLocation != null;
  }

  public boolean shouldTeleportToSpawnOnJoin() {
    return this.teleportToSpawnOnJoin;
  }

  public void setTeleportToSpawnOnJoin(boolean teleportToSpawnOnJoin) {
    this.teleportToSpawnOnJoin = teleportToSpawnOnJoin;
  }

  public int getDefaultBalance() {
    return this.defaultBalance;
  }

  public int getMinBalance() {
    return this.minBalance;
  }

  public int getMaxBalance() {
    return this.maxBalance;
  }

  public String getCurrencySymbol() {
    return currencySymbol;
  }

  public void setCurrencySymbol(String currencySymbol) {
    this.currencySymbol = currencySymbol;
  }

  public boolean isEconomyEnabled() {
    return economyEnabled;
  }

  public void setEconomyEnabled(boolean economyEnabled) {
    this.economyEnabled = economyEnabled;
  }

  public String getEconomyName() {
    return economyName;
  }

  public String getCurrencyNamePlural() {
    return currencyNamePlural;
  }

  public String getCurrencyNameSingular() {
    return currencyNameSingular;
  }
}
