package io.apexcreations.core;

import com.google.inject.Guice;
import io.apexcreations.core.cache.ApexConfigCache;
import io.apexcreations.core.cache.ApexMapCache;
import io.apexcreations.core.commands.CommandHandler;
import io.apexcreations.core.commands.SubCommand;
import io.apexcreations.core.database.DatabaseAdapter;
import io.apexcreations.core.listeners.JoinEvent;
import io.apexcreations.core.listeners.QuitEvent;
import io.apexcreations.core.modules.ModuleManager;
import io.apexcreations.core.modules.staff.ChatListener;
import io.apexcreations.core.players.ApexPlayer;
import java.util.UUID;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ApexCore extends JavaPlugin {
  private CommandHandler commandHandler;
  private final ApexMapCache<String, SubCommand> subCommandCache = new ApexMapCache<>(true);
  private final ApexMapCache<UUID, ApexPlayer> apexPlayerCache = new ApexMapCache<>(true);
  private final ApexConfigCache apexConfigCache = new ApexConfigCache();;
  private DatabaseAdapter databaseAdapter;
  private ModuleManager moduleManager;
  //private Injector injector;

  @Override
  public void onEnable() {
    Guice.createInjector(new DependencyModule(this));
    this.handleDatabase();
    this.handleListeners();
    this.commandHandler = new CommandHandler();
    this.commandHandler.handleCommands();
    this.moduleManager = new ModuleManager();
    this.moduleManager.handleModules();
  }

  private void handleListeners() {
    this.register(new JoinEvent(), new ChatListener(), new QuitEvent());
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
    this.databaseAdapter = new DatabaseAdapter(
        this.getConfig().getString("mysql.hostName"),
        this.getConfig().getInt("mysql.port"),
        this.getConfig().getString("mysql.userName"),
        this.getConfig().getString("mysql.password"),
        this.getConfig().getString("mysql.databaseName"));
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
