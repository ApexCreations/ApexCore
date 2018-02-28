package me.savvy.api.commands;

import java.util.Arrays;
import java.util.Optional;
import me.savvy.ApexAPI;
import me.savvy.ApexCore;
import me.savvy.api.builders.MessageBuilder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ApexCommand extends Command {

  private String name;
  private ApexCore apexCore;
  private String permission;
  private boolean playerOnly;

  public ApexCommand(String name) {
    this(name, false);
  }

  public ApexCommand(String name, boolean playerOnly) {
    this(name, "Apex Command", "apexcore." + name, playerOnly);
  }

  public ApexCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name);
    this.name = name;
    this.permission = permission;
    this.setDescription(description);
    this.setPermission(permission);
    this.playerOnly = playerOnly;
    this.setAliases(Arrays.asList(aliases));
    this.setPermissionMessage(ChatColor.RED + "You do not have permission for this command!");
  }

  // Maybe handle usage messages in here in the future
  @Override
  public boolean execute(CommandSender commandSender, String label, String[] args) {
    if (!(commandSender instanceof Player) && this.playerOnly) {
      MessageBuilder.create("&cThis command is for players only!").withPrefix().send(commandSender);
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
    return ApexCore.getInstance().getApexAPI().getSubCommandCache().search(args);
  }

  private void sendNoPermission(CommandSender commandSender) {
    MessageBuilder.create(ChatColor.RED + this.getPermissionMessage()).withPrefix()
        .send(commandSender);
  }

  protected ApexAPI getAPI() {
    return ApexCore.getInstance().getApexAPI();
  }
}