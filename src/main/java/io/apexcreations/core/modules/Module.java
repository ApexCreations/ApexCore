package io.apexcreations.core.modules;

import com.google.inject.Inject;
import io.apexcreations.core.ApexCore;
import java.util.logging.Logger;
import org.bukkit.configuration.file.FileConfiguration;

public abstract class Module {

    private final String name, description;
    private boolean enabled;
    private FileConfiguration config;
    @Inject
    private ApexCore apexCore;

    public Module(FileConfiguration config, String name, String description) {
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

    public FileConfiguration getConfig() {
        return this.config;
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