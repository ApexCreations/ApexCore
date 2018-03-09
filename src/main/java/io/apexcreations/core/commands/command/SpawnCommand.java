package io.apexcreations.core.commands.command;

import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand extends ApexCommand {

  public SpawnCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {
    Player player = (Player) commandSender;

    if (!this.getPlugin().getApexConfigCache().isSpawnSet()) { // Spawn has not yet been set
      MessageBuilder.create("&cSpawn has not yet been set.").send(player);
      return true;
    }

    // Replace this with a teleportation request.
    player.teleport(this.getPlugin().getApexConfigCache().getSpawnLocation());
    MessageBuilder.create("&aYou have been teleported to spawn!").send(player);
    return true;
  }
}
