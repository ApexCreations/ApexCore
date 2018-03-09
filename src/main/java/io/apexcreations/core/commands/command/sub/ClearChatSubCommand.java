package io.apexcreations.core.commands.command.sub;


import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.main.commands.SubCommand;
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

    MessageBuilder.create("&aYour chat has been cleared!").send(commandSender);
    MessageBuilder.create(String.format("&aChat has been for %s!", target.getName()))
        .send(commandSender);
  }
}
