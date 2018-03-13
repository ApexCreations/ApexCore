package io.apexcreations.core.cache;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.utils.Utils;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class ApexConfigCache {

    private final FileConfiguration config;
    private ApexCore apexCore;
    private boolean teleportToSpawnOnJoin;
    private Location spawnLocation;
    private String prefix;

    public ApexConfigCache(ApexCore apexCore) {
        this.apexCore = apexCore;
        this.config = this.apexCore.getConfig();
        this.load();
    }

    private void load() {
        this.teleportToSpawnOnJoin = this.config.getBoolean("spawn.teleportOnJoin", false);
        if (this.config.isSet("spawn.location")) {
            this.spawnLocation = Utils.fromString(this.config.getString("spawn.location"));
        }

        this.prefix = this.getConfig().getString("prefix", "&c&lApex&r");
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

    public String getPrefix() {
        return prefix;
    }
}
