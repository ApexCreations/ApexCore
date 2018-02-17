package me.savvy.api.commands;

import java.util.Arrays;
import java.util.Optional;
import me.savvy.ApexCore;
import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.players.ApexPlayer;
import me.savvy.main.cache.ApexPlayerCache;
import me.savvy.main.cache.SubCommandCache;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ApexCommand extends Command {

  private String name;
  private ApexCore apexCore;
  private String permission;
  private boolean playerOnly;

  public ApexCommand(String name) {
    this(name,false);
  }

  public ApexCommand(String name, boolean playerOnly) {
    this(name, "apexcore.*", "Apex Command", playerOnly);
  }

  public ApexCommand(String name, String description, String permission, boolean playerOnly, String... aliases) {
    super(name);
    this.name = name;
    this.permission = permission;
    this.setDescription(description);
    this.setPermission(permission);
    this.playerOnly = playerOnly;
    this.setAliases(Arrays.asList(aliases));
  }

  @Override
  public boolean execute(CommandSender commandSender, String label, String[] args) {
    if (!(commandSender instanceof Player) && this.playerOnly) {
      MessageBuilder.create("&cThis command is for players only!").send(commandSender);
      return true;
    }
    if (!commandSender.hasPermission(this.permission)) {
      MessageBuilder.create("&cYou do not have permission for this command!").send(commandSender);
      return true;
    }

    if (args.length > 0) {
      Optional<SubCommand> optionalSubCommand = this.getSubCommand(args[0]);
      optionalSubCommand.ifPresent(subCommand -> {
        if (!commandSender.hasPermission(subCommand.getPermission())) {
          this.sendNoPermission(commandSender);
          return;
        }
        subCommand.execute(commandSender, args);
      });
      return true;
    }

    return executeCommand(commandSender, label, args);
  }

  public abstract boolean executeCommand(CommandSender commandSender, String label, String[] args);

  private Optional<SubCommand> getSubCommand(String args) {
    return SubCommandCache.getInstance().search(args);
  }

  private void sendNoPermission(CommandSender commandSender) {
    // Replace with Language API messages(?)
    MessageBuilder
        .create("&cYou do not have permission for this command!")
        .send(commandSender);
  }
}