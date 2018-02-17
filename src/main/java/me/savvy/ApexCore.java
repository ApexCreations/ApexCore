package me.savvy;

import me.savvy.main.listeners.JoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ApexCore extends JavaPlugin {

  private static ApexCore instance;

  @Override
  public void onEnable() {
    instance = this;
    this.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
  }

  @Override
  public void onDisable() {
  }

  public static ApexCore getInstance() {
    return instance;
  }
}
