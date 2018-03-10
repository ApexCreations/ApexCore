package io.apexcreations.core.modules.economy.commands;

import io.apexcreations.core.commands.ApexCommand;
import io.apexcreations.core.modules.economy.commands.sub.EconomyGiveSubCommand;
import io.apexcreations.core.modules.economy.commands.sub.EconomyTakeSubCommand;
import org.bukkit.command.CommandSender;

public class EconomyCommand extends ApexCommand {

  public EconomyCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
    this.getApexCore().getSubCommandCache().add("give",
        new EconomyGiveSubCommand("give", "Deposit money into a player's account!",
            "apex.eco.give", false));
    this.getApexCore().getSubCommandCache().add("take",
        new EconomyTakeSubCommand("take", "Withdraw money from a player's account!",
            "apex.eco.take", false));
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

    // Send help message;
    return false;
  }
}
