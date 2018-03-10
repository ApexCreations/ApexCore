package io.apexcreations.core.commands;

import com.google.inject.Inject;
import io.apexcreations.core.ApexCore;
import io.apexcreations.core.modules.economy.commands.BalanceCommand;
import io.apexcreations.core.modules.chat.commands.ClearChatCommand;
import io.apexcreations.core.modules.economy.commands.EconomyCommand;
import io.apexcreations.core.commands.command.SetSpawnCommand;
import io.apexcreations.core.commands.command.SpawnCommand;
import io.apexcreations.core.commands.command.TeleportCommand;
import io.apexcreations.core.modules.staff.StaffChatCommand;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;

public class CommandHandler {

  @Inject
  private ApexCore apexCore;
  private CommandMap commandMap;

  public CommandHandler() {
    this.accessCommandMap();
  }

  public void handleCommands() {
    this.register(
        new StaffChatCommand("staffchat",
            "Use this for staff chat",
            "apex.clearchat", true, "sc"),

        new SetSpawnCommand("setspawn",
            "Set the spawn for the server/world",
            "apex.setspawn", true, "ss"),

        new SpawnCommand("spawn", "Teleport to spawn",
            "apex.spawn", true),

        new BalanceCommand("balance",
            "Check a player's balance!",
            "apex.balance", false, "bal", "money"),

        new EconomyCommand("economy",
            "Manage your player's balance!",
            "apex.economy", false, "eco"),

        new ClearChatCommand("clearchat",
            "Clear global chat!",
            "apex.clearchat", false, "cc"),

        new TeleportCommand("teleport",
            "Teleport to a player",
            "apex.teleport", true, "tp"));
  }

  public void register(ApexCommand... commands) {
    for (ApexCommand apexCommand : commands) {
      this.commandMap.register(apexCommand.getName(), apexCommand);
    }
  }

  private void accessCommandMap() {
    try {
      Field commandMap = this.apexCore.getServer().getClass().getDeclaredField("commandMap");
      commandMap.setAccessible(true);
      this.commandMap = (CommandMap) commandMap.get(Bukkit.getServer());
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  public void unregisterCommand(String command) {
    this.unregisterCommand(Bukkit.getPluginCommand(command));
  }

  private void unregisterCommand(PluginCommand cmd) {
    try {
      Object map = getPrivateField(this.commandMap, "knownCommands");
      @SuppressWarnings("unchecked")
      HashMap<String, ApexCommand> knownCommands =
          (HashMap<String, ApexCommand>) map;
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
