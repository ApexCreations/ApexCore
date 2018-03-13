package io.apexcreations.core.commands;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.builders.MessageBuilder;
import java.util.Arrays;
import java.util.Optional;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ApexCommand extends Command {

  private String name;
  private String permission;
  private boolean playerOnly;
  private ApexCore apexCore;
  public String PREFIX;

  public ApexCommand(ApexCore apexCore, String name) {
    this(apexCore, name, false);
  }

  public ApexCommand(ApexCore apexCore, String name, boolean playerOnly) {
    this(apexCore, name, "Apex Command", "apexcore." + name, playerOnly);
  }

  public ApexCommand(ApexCore apexCore, String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name);
    this.apexCore = apexCore;
    this.name = name;
    this.permission = permission;
    this.setDescription(description);
    this.setPermission(permission);
    this.playerOnly = playerOnly;
    this.setAliases(Arrays.asList(aliases));
    this.setPermissionMessage(ChatColor.RED + "You do not have permission for this command!");
    this.PREFIX = this.getApexCore().getApexConfigCache().getPrefix();
  }

  // Maybe handle usage messages in here in the future
  @Override
  public boolean execute(CommandSender commandSender, String label, String[] args) {
    if (!(commandSender instanceof Player) && this.playerOnly) {
      MessageBuilder.create("&cThis command is for players only!").withPrefix(PREFIX).send(commandSender);
      return true;
    }
    if (!commandSender.hasPermission(this.permission)) {
      this.sendNoPermission(commandSender);
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
    return this.getApexCore().getSubCommandCache().get(args);
  }

  private void sendNoPermission(CommandSender commandSender) {
    MessageBuilder.create(ChatColor.RED + this.getPermissionMessage()).withPrefix(PREFIX)
        .send(commandSender);
  }

  protected ApexCore getApexCore() {
    return this.apexCore;
  }
}