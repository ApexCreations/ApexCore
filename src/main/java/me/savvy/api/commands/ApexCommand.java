package me.savvy.api.commands;

import java.util.Arrays;
import java.util.Optional;
import me.savvy.ApexCore;
import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.players.ApexPlayer;
import me.savvy.main.cache.ApexPlayerCache;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class ApexCommand extends Command {

  private String name;
  private ApexCore apexCore;
  private String permission;

  public ApexCommand(String name) {
    this(name, "apexcore.*", "Apex Command");
  }

  public ApexCommand(String name, String description, String permission, String... aliases) {
    super(name);
    this.name = name;
    this.permission = permission;
    this.setDescription(description);
    this.setPermission(permission);
    this.setAliases(Arrays.asList(aliases));
  }

  @Override
  public boolean execute(CommandSender commandSender, String label, String[] args) {
    /*if (!(commandSender instanceof Player)) {
      MessageBuilder.create("&cThis command is for players only!").send(commandSender);
      return true;
    }*/
    if (!commandSender.hasPermission(permission)) {
      MessageBuilder.create("&cYou do not have permission for this command!").send(commandSender);
      return true;
    }
    Player player = (Player) commandSender;
    Optional<ApexPlayer> optionalApexPlayer = ApexPlayerCache.getInstance().get(player.getUniqueId());
    if (!optionalApexPlayer.isPresent()) {
      MessageBuilder
          .create("&cCould not find user data in database. Please contact an administrator!")
          .send(commandSender);
      return true;
    }
    if (args.length > 0) {
      Optional<SubCommand> optionalSubCommand = this.getSubCommand(args[0]);
    }
    return execute(optionalApexPlayer.get(), label, args);
  }

  public abstract boolean execute(ApexPlayer apexPlayer, String label, String[] args);

  private Optional<SubCommand> getSubCommand(String arg) {
    return Optional.empty();
  }
}