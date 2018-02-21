package me.savvy;

import me.savvy.main.listeners.JoinEvent;
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
