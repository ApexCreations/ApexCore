package me.savvy.main.commands.sub;

import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChatSubCommand extends SubCommand {

  public ClearChatSubCommand(String name, String info, String permission, boolean playerOnly,
      String... aliases) {
    super(name, info, permission, playerOnly, aliases);
  }

  @Override
  public void execute(CommandSender commandSender, String[] args) {
    Player target = Bukkit.getPlayer(args[0]);

    if (target == null) {
      MessageBuilder.create("&cThat player is not currently online!").send(commandSender);
      return;
    }

    for (int i = 0; i < 201; i++) {
      target.sendMessage(" ");
    }
    MessageBuilder.create(String.format("&aChat has been for %s!", target.getName()))
        .send(commandSender);
  }
}
