package me.savvy.main.commands.sub;

import me.savvy.api.commands.SubCommand;
import org.bukkit.command.CommandSender;

public class BalancePlayerSubCommand extends SubCommand {

  public BalancePlayerSubCommand(String name, String info, String permission, boolean playerOnly,
      String... aliases) {
    super(name, info, permission, playerOnly, aliases);
  }

  @Override
  public void execute(CommandSender commandSender, String[] args) {

  }
}
