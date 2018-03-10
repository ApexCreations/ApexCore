package io.apexcreations.core.modules.chat.commands;

import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import io.apexcreations.core.modules.chat.commands.sub.ClearChatSubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatCommand extends ApexCommand {

  public ClearChatCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
    this.getApexCore().getSubCommandCache().add("clear" ,new ClearChatSubCommand("Clear chat", "Clears chat", "apex.clearchat", false));
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
