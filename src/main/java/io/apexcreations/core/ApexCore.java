package io.apexcreations.core;

import com.google.inject.Guice;
import io.apexcreations.core.listeners.JoinEvent;
import io.apexcreations.core.listeners.QuitEvent;
import io.apexcreations.core.modules.Module;
import io.apexcreations.core.modules.chat.ChatModule;
import io.apexcreations.core.modules.chat.staff.ChatListener;
import io.apexcreations.core.modules.chat.staff.StaffModule;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ApexCore extends JavaPlugin {

  private CommandMap commandMap;
  private ApexAPI apexAPI;

  @Override
  public void onEnable() {
    Guice.createInjector(new DependencyModule(this));
    this.apexAPI = new ApexAPI();
    this.handleListeners();
    this.handleModules();
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
      apexAPI.getApexModuleCache().add(module.getName(), module);
    }
  }

  @Override
  public void onDisable() {
    this.getApexAPI().getApexModuleCache().getMap().values().forEach(Module::terminate);
    this.apexAPI.getApexConfigCache().save();
  }

  public ApexAPI getApexAPI() {
    return this.apexAPI;
  }

  private void handleModules() {
    register(new ChatModule("Chat Module", "Handles all chat related activities"));
    register(new StaffModule("Staff module", "For things like staff chat and staff mode"));
  }
}
