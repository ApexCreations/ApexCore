package io.apexcreations.core.main.commands;

import io.apexcreations.core.api.commands.ApexCommand;
import io.apexcreations.core.main.commands.sub.EconomyGiveSubCommand;
import io.apexcreations.core.main.commands.sub.EconomyTakeSubCommand;

import org.bukkit.command.CommandSender;

public class EconomyCommand extends ApexCommand {

  public EconomyCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
    this.getAPI().getSubCommandCache().add(
        new EconomyGiveSubCommand("give", "Deposit money into a player's account!",
            "apex.eco.give", false));
    this.getAPI().getSubCommandCache().add(
        new EconomyTakeSubCommand("take", "Withdraw money from a player's account!",
            "apex.eco.take", false));
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

    // Send help message;
    return false;
  }
}
