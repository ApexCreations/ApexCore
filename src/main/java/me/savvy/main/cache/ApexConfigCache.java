package me.savvy.main.cache;

import me.savvy.ApexCore;
import me.savvy.main.utils.Utils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ApexConfigCache {

  private final ApexCore apexCore;
  private final FileConfiguration config;

  private boolean teleportToSpawnOnJoin;
  private Location spawnLocation;

  public ApexConfigCache(ApexCore apexCore) {
    this.apexCore = apexCore;
    this.config = this.apexCore.getConfig();
    this.load();
  }

  private void load() {
    this.teleportToSpawnOnJoin = this.config.getBoolean("teleportOnJoin", false);
    if (this.config.isSet("spawn.location")) {
      this.spawnLocation = Utils.fromString(this.config.getString("spawn.location"));
    }
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
}
