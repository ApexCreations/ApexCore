package io.apexcreations.core.commands.command;

import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends ApexCommand {

  public SpawnCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {
      if (!this.getApexCore().getApexConfigCache().isSpawnSet()) { // Spawn has not yet been set
          MessageBuilder.create("&cSpawn has not yet been set.").send(commandSender);
          return true;
      }
      if (args.length == 0) {
          Player player = (Player) commandSender;
          // Replace this with a teleportation request.
          player.teleport(this.getApexCore().getApexConfigCache().getSpawnLocation());
          MessageBuilder.create("&aYou have been teleported to spawn!").send(player);
          return true;
      }
      Player target = Bukkit.getPlayer(args[0]);
      
      if (target == null) {
          MessageBuilder.create("&4Target player could not be found").send(commandSender);
          return true;
      }
      target.teleport(this.getApexCore().getApexConfigCache().getSpawnLocation());
      MessageBuilder.create(String.format("&2You have teleported %s to spawn", ChatColor.YELLOW + target.getName()));
      MessageBuilder.create("&2You have been teleported to spawn!").send(target);
      return true;
  }
}
