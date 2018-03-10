package io.apexcreations.core;

import com.google.inject.Guice;
import io.apexcreations.core.cache.ApexConfigCache;
import io.apexcreations.core.cache.ApexMapCache;
import io.apexcreations.core.commands.CommandHandler;
import io.apexcreations.core.commands.SubCommand;
import io.apexcreations.core.database.DatabaseAdapter;
import io.apexcreations.core.listeners.JoinEvent;
import io.apexcreations.core.listeners.QuitEvent;
import io.apexcreations.core.modules.Module;
import io.apexcreations.core.modules.chat.ChatModule;
import io.apexcreations.core.modules.staff.ChatListener;
import io.apexcreations.core.modules.staff.StaffModule;
import io.apexcreations.core.modules.economy.EconomyModule;
import io.apexcreations.core.players.ApexPlayer;
import java.util.UUID;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ApexCore extends JavaPlugin {
  private CommandHandler commandHandler;
  private final ApexMapCache<String, SubCommand> subCommandCache = new ApexMapCache<>(true);
  private final ApexMapCache<UUID, ApexPlayer> apexPlayerCache = new ApexMapCache<>(true);
  private final ApexMapCache<String, Module> apexModuleCache = new ApexMapCache<>(true);
  private final ApexConfigCache apexConfigCache = new ApexConfigCache();;
  private DatabaseAdapter databaseAdapter;
  //private Injector injector;

  @Override
  public void onEnable() {
    Guice.createInjector(new DependencyModule(this));
    this.handleDatabase();
    this.handleListeners();
    this.handleModules();
    this.commandHandler = new CommandHandler();
    this.commandHandler.handleCommands();
  }

  private void handleListeners() {
    this.register(new JoinEvent(), new ChatListener(), new QuitEvent());
  }

  private void register(Listener... listeners) {
    for (Listener listener : listeners) {
      this.getServer().getPluginManager().registerEvents(listener, this);
    }
  }

  private void register(Module... modules) {
    for (Module module : modules) {
      this.getApexModuleCache().add(module.getName(), module);
    }
  }

  @Override
  public void onDisable() {
    this.getApexModuleCache().getMap().values().forEach(Module::terminate);
    this.getApexConfigCache().save();
  }

  private void handleModules() {
    register(new ChatModule("Chat Module", "Handles all chat related activities"));
    register(new StaffModule("Staff module", "For things like staff chat and staff mode"));
    register(new EconomyModule("Economy Module", "For player balances and server economy"));
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

  public ApexMapCache<String, Module> getApexModuleCache() {
    return this.apexModuleCache;
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
