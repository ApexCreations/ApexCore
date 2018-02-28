package me.savvy.main.commands;

import me.savvy.api.commands.ApexCommand;
import me.savvy.main.commands.sub.EconomyGiveSubCommand;
import me.savvy.main.commands.sub.EconomyTakeSubCommand;
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
