package io.apexcreations.core.api.modules;

import io.apexcreations.core.ApexCore;
import java.io.File;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public abstract class Module {

  private final String name, description;
  private File file;
  private FileConfiguration config;
  private boolean enabled;

  public Module(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public abstract void initialize();

  public abstract void terminate();

  public void reload() {
    if (this.isEnabled()) {
      this.terminate();
    }
    this.initialize();
  }

  public String getName() {
    return this.name;
  }

  public String getDescription() {
    return this.description;
  }

  public boolean isEnabled() {
    return this.enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  protected void generateConfig() {
    this.getPlugin()
        .saveResource(String.format("modules/%s.yml", this.getName().toLowerCase()), false);
    this.config = YamlConfiguration.loadConfiguration(
        new File(this.getPlugin().getDataFolder(),
            String.format("modules/%s.yml", this.getName().toLowerCase())));
  }

  public File getFile() {
    return this.file;
  }

  public FileConfiguration getConfig() {
    return this.config;
  }

  protected ApexCore getPlugin() {
    return ApexCore.getInstance();
  }

  protected Logger getLogger() {
    return ApexCore.getInstance().getLogger();
  }
}