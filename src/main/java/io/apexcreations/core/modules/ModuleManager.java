package io.apexcreations.core.modules;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.cache.ApexMapCache;
import io.apexcreations.core.modules.chat.ChatModule;
import io.apexcreations.core.modules.economy.EconomyModule;
import io.apexcreations.core.modules.staff.StaffModule;
import java.io.File;
import java.util.Arrays;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ModuleManager {

    private final ApexMapCache<String, Module> moduleCache = new ApexMapCache<>(true);
    private final FileConfiguration config;
    private final File file;
    private ApexCore apexCore;

    public ModuleManager(ApexCore apexCore) {
        this.apexCore = apexCore;
        this.apexCore.saveResource(this.apexCore.getDataFolder() + "/modules/modules.yml", false);
        this.file = new File(this.apexCore.getDataFolder(), "/modules/modules.yml");
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public void handleModules() {
        this.register(
                new ChatModule(apexCore, config, "Chat", "Handles all chat related activities"),

                new StaffModule(apexCore, config, "Staff", "For things like staff chat and staff mode"),

                new EconomyModule(apexCore, config, "Economy", "For player balances and server economy"));
    }

    public void handleFullTermination() {
        this.moduleCache.getMap().values().forEach(Module::terminate);
    }


    public void register(Module... modules) {
        Arrays.stream(modules).forEach(this::register);
    }

    private void register(Module module) {
        if (!this.config.isSet(module.getName())) {
            this.apexCore.getLogger()
                    .warning("Could not find configuration section for module `%s`. Disabling...");
            module.setEnabled(false);
            return;
        }
        module.setEnabled(this.config.getBoolean(module.getSimpleName() + ".enabled"));
        this.moduleCache.add(module.getName(), module);
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }

    public ApexMapCache<String, Module> getModuleCache() {
        return this.moduleCache;
    }
}
