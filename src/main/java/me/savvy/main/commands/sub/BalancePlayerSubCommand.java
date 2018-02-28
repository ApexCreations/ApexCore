package me.savvy.main.commands.sub;

import java.util.Optional;
import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.commands.SubCommand;
import me.savvy.api.players.ApexPlayer;
import me.savvy.main.account.Account;
import me.savvy.main.utils.Utils;
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

    Optional<ApexPlayer> optionalApexPlayer = this.getAPI().getPlayerCache().get(player);

    if (!optionalApexPlayer.isPresent()) {
      MessageBuilder.create("&c&lERROR &7Could not find player data!").withPrefix().send(commandSender);
      return;
    }

    ApexPlayer apexPlayer = optionalApexPlayer.get();

    if (apexPlayer.getAccount() == null) {
      MessageBuilder.create("&c&lERROR &7Could not find player account!").withPrefix().send(commandSender);
      return;
    }

    Account account = apexPlayer.getAccount();

    MessageBuilder.create("&a&l" + player.getName() + "'s BALANCE &7&l>> &a&l" + Utils.formatCurrency(account.getBalance()))
    .send(commandSender);

  }
}
