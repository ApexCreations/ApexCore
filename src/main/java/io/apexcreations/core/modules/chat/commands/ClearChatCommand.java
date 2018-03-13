package io.apexcreations.core.modules.chat.commands;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// TODO: Base command /chat with subcommands clear, mute, slow
public class ClearChatCommand extends ApexCommand {

  public ClearChatCommand(ApexCore apexCore, String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(apexCore, name, description, permission, playerOnly, aliases);
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {
      if (args.length == 0) {
          Bukkit.getOnlinePlayers().stream().filter(player ->
                  (!player.hasPermission("apex.clearchat.exempt"))).forEach(this::clearChat);
          MessageBuilder.create("&aChat has been cleared!").send(commandSender);
          return false;
      }
      Player target = Bukkit.getPlayer(args[0]);
      
      if (target == null) {
          MessageBuilder.create("&4Target player not found").send(commandSender);
          return true;
      }
      // Wouldn't want a mod to clear an admin's chat
      if (target.hasPermission("apex.clearchat.exempt")) {
          MessageBuilder.create("&4You cannot clear this player's chat!").send(commandSender);
          return true;
      }
      clearChat(target);
      MessageBuilder.create("&2Your chat has been cleared!").send(target);
      MessageBuilder.create(String.format("%s&2's chat has been cleared", ChatColor.YELLOW + target.getName()));
      return true;
  }

  private void clearChat(Player player) {
    for (int i = 0; i < 201; i++) {
      player.sendMessage(" ");
    }
  }
}
