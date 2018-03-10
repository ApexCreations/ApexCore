package io.apexcreations.core.commands;


import com.google.inject.Inject;
import io.apexcreations.core.ApexCore;
import org.bukkit.command.CommandSender;

public abstract class SubCommand {

  private String name, info, permission;
  private boolean playerOnly;
  private String[] aliases;
  @Inject
  private ApexCore apexCore;

  public SubCommand(String name, String info, boolean playerOnly) {
    this(name, info, "", playerOnly);
  }

  public SubCommand(String name, String info, String permission, boolean playerOnly,
      String... aliases) {
    this.name = name;
    this.info = info;
    this.permission = permission;
    this.aliases = aliases;
    this.playerOnly = playerOnly;
  }

  public abstract void execute(CommandSender commandSender, String[] args);

  public boolean isPlayerOnly() {
    return this.playerOnly;
  }

  public String getName() {
    return this.name;
  }

  public String getInfo() {
    return this.info;
  }

  public String[] getAliases() {
    return this.aliases;
  }

  public String getPermission() {
    return this.permission;
  }

  protected ApexCore getApexCore() {
    return apexCore;
  }
}