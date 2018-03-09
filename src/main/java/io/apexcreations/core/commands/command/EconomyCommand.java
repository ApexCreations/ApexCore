package io.apexcreations.core.commands.command;

import io.apexcreations.core.commands.ApexCommand;
import io.apexcreations.core.commands.command.sub.EconomyGiveSubCommand;
import io.apexcreations.core.commands.command.sub.EconomyTakeSubCommand;
import org.bukkit.command.CommandSender;

public class EconomyCommand extends ApexCommand {

  public EconomyCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
    this.getAPI().getSubCommandCache().add("give",
        new EconomyGiveSubCommand("give", "Deposit money into a player's account!",
            "apex.eco.give", false));
    this.getAPI().getSubCommandCache().add("take",
        new EconomyTakeSubCommand("take", "Withdraw money from a player's account!",
            "apex.eco.take", false));
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

    // Send help message;
    return false;
  }
}
