package me.savvy;

import java.lang.reflect.Field;
import java.util.HashMap;
import me.savvy.api.commands.ApexCommand;
import me.savvy.api.modules.Module;
import me.savvy.main.listeners.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ApexCore extends JavaPlugin {

  private static ApexCore instance;
  private CommandMap commandMap;
  private ApexAPI apexAPI;

  public static ApexCore getInstance() {
    return instance;
  }

  @Override
  public void onEnable() {
    instance = this;
    this.apexAPI = new ApexAPI();
    this.handleListeners();
    this.handleCommands();
  }

  private void handleListeners() {
    this.register(new JoinEvent());
  }

  private void handleCommands() {
    this.accessCommandMap();
  }

  public void register(Listener... listeners) {
    for (Listener listener : listeners) {
      this.getServer().getPluginManager().registerEvents(listener, this);
    }
  }

  public void register(ApexCommand... commands) {
    for (ApexCommand nemeCommand : commands) {
      this.commandMap.register(nemeCommand.getName(), nemeCommand);
    }
  }

  @Override
  public void onDisable() {
    this.getApexAPI().getApexModuleCache().getAllModules().forEach(Module::terminate);
  }

  public ApexAPI getApexAPI() {
    return this.apexAPI;
  }

  private void accessCommandMap() {
    try {
      Field commandMap = this.getServer().getClass().getDeclaredField("commandMap");
      commandMap.setAccessible(true);
      this.commandMap = (CommandMap) commandMap.get(Bukkit.getServer());
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  public void unregisterCommand(String command) {
    this.unregisterCommand(Bukkit.getPluginCommand(command));
  }

  public void unregisterCommand(PluginCommand cmd) {
    try {
      Object map = getPrivateField(this.commandMap, "knownCommands");
      @SuppressWarnings("unchecked")
      HashMap<String, ApexCommand> knownCommands = (HashMap<String, ApexCommand>) map;
      knownCommands.remove(cmd.getName());
      for (String alias : cmd.getAliases()) {
        knownCommands.remove(alias);
      }
    } catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  private Object getPrivateField(Object object, String field) throws SecurityException,
      NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    Class<?> clazz = object.getClass();
    Field objectField = clazz.getDeclaredField(field);
    objectField.setAccessible(true);
    Object result = objectField.get(object);
    objectField.setAccessible(false);
    return result;
  }
}
