package io.apexcreations.core.main.commands;

import io.apexcreations.core.api.builders.MessageBuilder;
import io.apexcreations.core.api.commands.ApexCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand extends ApexCommand {

  public ClearChatCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
    //this.getAPI().getSubCommandCache().add(new ClearChatSubCommand());
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

    Bukkit.getOnlinePlayers().stream().filter(player ->
        (!player.hasPermission("apex.clearchat.exempt"))).forEach(this::clearChat);
    MessageBuilder.create("&aChat has been cleared!").send(commandSender);
    return false;
  }

  private void clearChat(Player player) {
    for (int i = 0; i < 201; i++) {
      player.sendMessage(" ");
    }
  }
}
