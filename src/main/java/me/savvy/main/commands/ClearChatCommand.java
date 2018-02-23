package me.savvy.main.commands;

import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.commands.ApexCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand extends ApexCommand {

  public ClearChatCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

    Bukkit.getOnlinePlayers().stream().filter(player ->
        (!player.hasPermission(this.getPermission()))).forEach(this::clearChat);
    MessageBuilder.create("&aChat has been cleared!").send(commandSender);
    return false;
  }

  private void clearChat(Player player) {
    for (int i = 0; i < 201; i++) {
      player.sendMessage(" ");
    }
  }
}
