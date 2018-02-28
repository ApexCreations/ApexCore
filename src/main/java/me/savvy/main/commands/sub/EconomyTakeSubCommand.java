package me.savvy.main.commands.sub;

import java.util.Arrays;
import java.util.Optional;
import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.commands.SubCommand;
import me.savvy.api.players.ApexPlayer;
import me.savvy.main.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyTakeSubCommand extends SubCommand {

  public EconomyTakeSubCommand(String name, String info, String permission, boolean playerOnly,
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
          .format("&c&lERROR &7Incorrect Usage! Try /%s take <player> <amount>",
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
    apexPlayer.getAccount().removeFromBalance(amount);

    MessageBuilder.create("&a&lWITHDRAW &a" + amount + " &7has been withdrawn from your account!")
        .send(player);

    MessageBuilder.create(String
        .format("&a&lWITHDRAW &a%s &7has been withdrawn from %s's account!", amount,
            player.getName())).send(commandSender);
  }
}
