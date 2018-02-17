package me.savvy.api.builders;

import org.bukkit.command.CommandSender;

public class MessageBuilder {

  public static MessageBuilder create(String s) {
    return new MessageBuilder();
  }

  public void send(CommandSender commandSender) {
  }
}
