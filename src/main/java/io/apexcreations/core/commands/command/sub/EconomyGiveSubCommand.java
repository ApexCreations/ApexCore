package io.apexcreations.core.commands.command.sub;

import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.exceptions.MaxMoneyException;
import io.apexcreations.core.main.commands.SubCommand;
import io.apexcreations.core.players.ApexPlayer;
import io.apexcreations.core.utils.Utils;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyGiveSubCommand extends SubCommand {

  public EconomyGiveSubCommand(String name, String info, String permission, boolean playerOnly,
      String... aliases) {
    super(name, info, permission, playerOnly, aliases);
  }

  @Override
  public void execute(CommandSender commandSender, String[] args) {
    args = Arrays.copyOfRange(args, 1, args.length);
    System.out.println(Arrays.toString(args));
    Player player = Bukkit.getPlayer(args[0]);

    if (player == null) {
      MessageBuilder.create("&c&lERROR &7&l>> &cCould not find player!").send(commandSender);
      return;
    }

    Optional<ApexPlayer> optionalApexPlayer = this.getAPI().getPlayerCache()
        .get(player.getUniqueId());

    if (!optionalApexPlayer.isPresent()) {
      MessageBuilder.create("&c&lERROR &7&l>> &cCould not find player data!")
          .send(commandSender);
      return;
    }

    if (!Utils.isDouble(args[1])) {
      MessageBuilder.create(String
          .format("&c&lERROR &7&l>> &cIncorrect Usage! Try /%s give <player> <amount>",
              this.getName())).send(commandSender);
      return;
    }

    ApexPlayer apexPlayer = optionalApexPlayer.get();

    if (apexPlayer.getAccount() == null) {
      MessageBuilder.create("&c&lERROR &7&l>> &cCould not find player account!").withPrefix()
          .send(commandSender);
      return;
    }

    double amount = Double.parseDouble(args[1]);

    try {
      apexPlayer.getAccount().addToBalance(amount);
    } catch (MaxMoneyException e) {
      MessageBuilder.create("&c&lERROR &7" + e.getMessage()).send(commandSender);
      return;
    }

    String amt = Utils.formatCurrency(BigDecimal.valueOf(amount));
    String currency = this.getAPI().getApexConfigCache().getCurrencySymbol();

    MessageBuilder.create(String.
        format("&a&lDEPOSIT &7&l>> &a&l%s%s &7has been deposited into your account!", currency,
            amt))
        .send(player);

    MessageBuilder.create(String
        .format("&a&lDEPOSIT &7&l>> &a&l%s%s &7has been deposited into %s's account!", currency,
            amt,
            player.getName())).send(commandSender);
  }
}
