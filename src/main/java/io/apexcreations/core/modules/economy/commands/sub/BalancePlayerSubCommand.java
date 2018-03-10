package io.apexcreations.core.modules.economy.commands.sub;

import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.SubCommand;
import io.apexcreations.core.modules.economy.account.Account;
import io.apexcreations.core.players.ApexPlayer;
import io.apexcreations.core.utils.Utils;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalancePlayerSubCommand extends SubCommand {

  public BalancePlayerSubCommand(String name, String info, String permission, boolean playerOnly,
      String... aliases) {
    super(name, info, permission, playerOnly, aliases);
  }

  @Override
  public void execute(CommandSender commandSender, String[] args) {

    Player player = Bukkit.getPlayer(args[0]);

    if (player == null) {
      MessageBuilder.create("&c&lERROR &7Could not find player!").withPrefix().send(commandSender);
      return;
    }

    Optional<ApexPlayer> optionalApexPlayer = this.getApexCore().getPlayerCache()
        .get(player.getUniqueId());

    if (!optionalApexPlayer.isPresent()) {
      MessageBuilder.create("&c&lERROR &7Could not find player data!").withPrefix()
          .send(commandSender);
      return;
    }

    ApexPlayer apexPlayer = optionalApexPlayer.get();

    if (apexPlayer.getAccount() == null) {
      MessageBuilder.create("&c&lERROR &7Could not find player account!").withPrefix()
          .send(commandSender);
      return;
    }

    Account account = apexPlayer.getAccount();

    MessageBuilder.create("&a&l" + player.getName() + "'s BALANCE &7&l>> &a&l" + Utils
        .formatCurrency(account.getBalance()))
        .send(commandSender);

  }
}
