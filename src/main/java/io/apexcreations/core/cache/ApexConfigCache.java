package io.apexcreations.core.cache;

import com.google.inject.Inject;
import io.apexcreations.core.ApexCore;
import io.apexcreations.core.utils.Utils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ApexConfigCache {

  private final FileConfiguration config;
  @Inject
  private ApexCore apexCore;
  private boolean teleportToSpawnOnJoin;
  private Location spawnLocation;

  public ApexConfigCache() {
    this.config = this.apexCore.getConfig();
    this.load();
  }

  private void load() {
    this.teleportToSpawnOnJoin = this.config.getBoolean("spawn.teleportOnJoin", false);
    if (this.config.isSet("spawn.location")) {
      this.spawnLocation = Utils.fromString(this.config.getString("spawn.location"));
    }
  }

  public void save() {
    this.config.set("spawn.teleportOnJoin", this.teleportToSpawnOnJoin);
    if (this.spawnLocation != null) {
      this.config.set("spawn.location", Utils.toString(this.spawnLocation));
    }
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
}
