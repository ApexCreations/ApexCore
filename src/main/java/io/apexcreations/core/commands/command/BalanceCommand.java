package io.apexcreations.core.commands.command;

import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import io.apexcreations.core.commands.command.sub.BalancePlayerSubCommand;
import io.apexcreations.core.main.modules.economy.account.Account;
import io.apexcreations.core.players.ApexPlayer;
import io.apexcreations.core.utils.Utils;
import java.util.Optional;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand extends ApexCommand {

  public BalanceCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
    this.getPlugin().getSubCommandCache().add("other",
        new BalancePlayerSubCommand("other", "Check the balance of a player's account!",
            "apex.balance.other", false));
  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

    Player player = (Player) commandSender;

    Optional<ApexPlayer> optionalApexPlayer = this.getPlugin().getPlayerCache()
        .get(player.getUniqueId());

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
    String currency = this.getPlugin().getApexConfigCache().getCurrencySymbol();
    MessageBuilder
        .create("&a&lBALANCE &7&l>> &a&l" + currency + Utils.formatCurrency(account.getBalance()))
        .send(commandSender);

    return false;
  }
}
