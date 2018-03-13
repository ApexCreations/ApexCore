package io.apexcreations.core.commands.command;


import io.apexcreations.core.ApexCore;
import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends ApexCommand {

  public SetSpawnCommand(ApexCore apexCore, String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(apexCore, name, description, permission, playerOnly, aliases);
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

    Player player = (Player) commandSender;

    this.getApexCore().getApexConfigCache().setSpawnLocation(player.getLocation());
    MessageBuilder.create("&aYou have successfully updated server spawn location").send(player);
    return false;
  }
}
