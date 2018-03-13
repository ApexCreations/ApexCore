package io.apexcreations.core;

import io.apexcreations.core.cache.ApexConfigCache;
import io.apexcreations.core.cache.ApexMapCache;
import io.apexcreations.core.commands.CommandHandler;
import io.apexcreations.core.commands.SubCommand;
import io.apexcreations.core.database.DatabaseAdapter;
import io.apexcreations.core.database.MySQL;
import io.apexcreations.core.listeners.JoinEvent;
import io.apexcreations.core.listeners.QuitEvent;
import io.apexcreations.core.modules.ModuleManager;
import io.apexcreations.core.players.ApexPlayer;
import java.util.UUID;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ApexCore extends JavaPlugin {

    private final ApexMapCache<String, SubCommand> subCommandCache = new ApexMapCache<>(true);
    private final ApexMapCache<UUID, ApexPlayer> apexPlayerCache = new ApexMapCache<>(true);
    private final ApexConfigCache apexConfigCache = new ApexConfigCache(this);
    private CommandHandler commandHandler;
    private DatabaseAdapter databaseAdapter;
    private ModuleManager moduleManager;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        this.handleDatabase();
        this.handleListeners();
        this.commandHandler = new CommandHandler(this);
        this.commandHandler.handleCommands();
        this.moduleManager = new ModuleManager(this);
        this.moduleManager.handleModules();
    }

    private void handleListeners() {
        this.register(
                // new ChatListener(this), This should be registered/unregistered when the module is enabled and disabled
                new JoinEvent(this),
                new QuitEvent(this));
    }

    private void register(Listener... listeners) {
        for (Listener listener : listeners) {
            this.getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    @Override
    public void onDisable() {
        this.getModuleManager().handleFullTermination();
        this.getApexConfigCache().save();
    }

    private void handleDatabase() {
        if (!getConfig().getBoolean("mysql.enabled")) {
            return;
        }
        try {
            this.databaseAdapter = new DatabaseAdapter(this, new MySQL.ConnectionBuilder()
                    .hostName(this.getConfig().getString("mysql.hostName"))
                    .port(this.getConfig().getInt("mysql.port"))
                    .databaseName(this.getConfig().getString("mysql.databaseName"))
                    .userName(this.getConfig().getString("mysql.userName"))
                    .password(this.getConfig().getString("mysql.password")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ModuleManager getModuleManager() {
        return this.moduleManager;
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
}
