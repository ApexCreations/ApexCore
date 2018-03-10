package io.apexcreations.core.modules;

import com.google.inject.Inject;
import io.apexcreations.core.ApexCore;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class Module {

  private final String name, description;
  private boolean enabled;
  @Inject private ApexCore apexCore;

  public Module(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public abstract void initialize();

  public abstract void terminate();

    public abstract void loadConfig(FileConfiguration config);
    public abstract void saveConfig(FileConfiguration config);

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

  protected ApexCore getPlugin() {
    return this.apexCore;
  }

  protected Logger getLogger() {
    return this.apexCore.getLogger();
  }
}