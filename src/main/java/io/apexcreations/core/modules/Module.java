package io.apexcreations.core.modules;

import io.apexcreations.core.ApexCore;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class Module {

    private final String name, description;
    private final FileConfiguration config;
    private boolean enabled;
    private ApexCore apexCore;

    public Module(ApexCore apexCore, FileConfiguration config, String name, String description) {
        this.apexCore = apexCore;
        this.name = name;
        this.description = description;
        this.config = config;
    }

    public abstract void initialize();

    public abstract void terminate();

    public abstract void saveConfig();

    public void reload() {
        if (this.isEnabled()) {
            this.terminate();
        }
        this.initialize();
    }

    public String getName() {
        return this.name;
    }

    public String getSimpleName() {
        return this.name.replaceAll("\\s+", "");
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

    public FileConfiguration getConfig() {
        return config;
    }
}