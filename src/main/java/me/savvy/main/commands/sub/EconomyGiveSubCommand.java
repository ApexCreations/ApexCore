package me.savvy.main.commands.sub;

import java.util.Arrays;
import java.util.Optional;
import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.commands.SubCommand;
import me.savvy.api.exceptions.MaxMoneyException;
import me.savvy.api.players.ApexPlayer;
import me.savvy.main.utils.Utils;
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
    args = Arrays.copyOfRange(args, 1, args.length - 1);

    Player player = Bukkit.getPlayer(args[0]);

    if (player == null) {
      MessageBuilder.create("&c&lERROR &7Could not find player!").withPrefix().send(commandSender);
      return;
    }

    Optional<ApexPlayer> optionalApexPlayer = this.getAPI().getPlayerCache().get(player);

    if (!optionalApexPlayer.isPresent()) {
      MessageBuilder.create("&c&lERROR &7Could not find player data!").withPrefix()
          .send(commandSender);
      return;
    }

    if (!Utils.isDouble(args[1])) {
      MessageBuilder.create(String
          .format("&c&lERROR &7Incorrect Usage! Try /%s give <player> <amount>",
              this.getName())).send(commandSender);
      return;
    }

    ApexPlayer apexPlayer = optionalApexPlayer.get();

    if (apexPlayer.getAccount() == null) {
      MessageBuilder.create("&c&lERROR &7Could not find player account!").withPrefix()
          .send(commandSender);
      return;
    }

    double amount = Double.parseDouble(args[1]);
    try {
      apexPlayer.getAccount().addToBalance(amount);
    } catch (MaxMoneyException e) {
      MessageBuilder.create("&c&lERROR &7" + e.getMessage());
    }

    MessageBuilder.create("&a&lDEPOSIT &a" + amount + " &7has been deposited into your account!")
        .send(player);

    MessageBuilder.create(String
        .format("&a&lDEPOSIT &a%s &7has been deposited into %s's account!", amount,
            player.getName())).send(commandSender);
  }
}
