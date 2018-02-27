package me.savvy.main.commands;

import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.commands.ApexCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand extends ApexCommand {

  public SetSpawnCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

    Player player = (Player) commandSender;

    this.getAPI().getApexConfigCache().setSpawnLocation(player.getLocation());
    MessageBuilder.create("&aYou have successfully updated server spawn location").send(player);
    return false;
  }
}
