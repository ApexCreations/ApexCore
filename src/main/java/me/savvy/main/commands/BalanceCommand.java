package me.savvy.main.commands;

import java.util.Optional;
import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.commands.ApexCommand;
import me.savvy.api.players.ApexPlayer;
import me.savvy.main.account.Account;
import me.savvy.main.commands.sub.BalancePlayerSubCommand;
import me.savvy.main.utils.Utils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand extends ApexCommand {

  public BalanceCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
    this.getAPI().getSubCommandCache().add(
        new BalancePlayerSubCommand("other", "Check the balance of a player's account!",
            "apex.balance.other", false));
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

    Player player = (Player) commandSender;

    Optional<ApexPlayer> optionalApexPlayer = this.getAPI().getPlayerCache().get(player);

    if (!optionalApexPlayer.isPresent()) {
      MessageBuilder.create("&cCould not find player data!").withPrefix().send(commandSender);
      return false;
    }

    ApexPlayer apexPlayer = optionalApexPlayer.get();

    if (apexPlayer.getAccount() == null) {
      MessageBuilder.create("&cCould not find player account!").withPrefix().send(commandSender);
      return false;
    }

    Account account = apexPlayer.getAccount();
    String currency = this.getAPI().getApexConfigCache().getCurrencySymbol();
    MessageBuilder.create("&a&lBALANCE &7&l>> &a&l" + currency + Utils.formatCurrency(account.getBalance()))
        .send(commandSender);

    return false;
  }
}
