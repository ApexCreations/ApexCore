package io.apexcreations.core.commands.command;

import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand extends ApexCommand {

  public TeleportCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
    this.setUsage(ChatColor.RED + "/" + this.getLabel() + " <player>");

  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {
    Player player = (Player) commandSender;

    if (args.length != 1) {
      MessageBuilder.create(this.getUsage()).send(player);
      return false;
    }

    Player target = Bukkit.getPlayer(args[0]);

    if (target == null) {
      MessageBuilder.create("Player can not be found!").send(player);
      return false;
    }

    if (target.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty()) {
      MessageBuilder.create(String
          .format("%s is standing on a unsafe location, please try again later",
              target.getPlayer().getName())).send(player);
      return false;
    }
    MessageBuilder.create(String.format("&aTeleporting you to %s", target.getName()))
        .send(commandSender);
    player.teleport(target);
    return true;
  }
}
