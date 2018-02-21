package me.savvy;

import me.savvy.api.commands.ApexCommand;
import me.savvy.main.listeners.JoinEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class ApexCore extends JavaPlugin {

  private static ApexCore instance;
  private ApexAPI apexAPI;

  @Override
  public void onEnable() {
    instance = this;
    this.apexAPI = new ApexAPI();
    this.handleListeners();
  }

  private void handleListeners() {
      this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
  }
  
  private void registerCommands() {
      
        
  }

  @Override
  public void onDisable() {
  }

  public ApexAPI getApexAPI() {
    return this.apexAPI;
  }

  public static ApexCore getInstance() {
    return instance;
  }
}
