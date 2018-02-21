package me.savvy.main.modules.staffchat;

import java.util.Optional;
import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.commands.ApexCommand;
import me.savvy.api.players.ApexPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand extends ApexCommand {

  public StaffChatCommand(String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(name, description, permission, playerOnly, aliases);
    this.setUsage(ChatColor.RED + "/" + this.getLabel() + " <message>");

  }

  @Override
  public boolean executeCommand(CommandSender commandSender, String label, String[] args) {
    // blind casting because of this framework
    Player player = (Player) commandSender;

    if (!(args.length >= 1)) {
      MessageBuilder.create(this.getUsage()).send(player);
      return false;
    }

    Optional<ApexPlayer> optionalPlayer = this.getAPI().getPlayerCache().get(player);

    if (!optionalPlayer.isPresent()) {
      // Player can not be found please handle this.
      return false;
    }

    ApexPlayer apexPlayer = optionalPlayer.get();

    apexPlayer.setStaffChat(!apexPlayer.isInStaffChat());

    if (apexPlayer.isInStaffChat()) {
      // Player is now in staff chat.
    } else {
      // Player no longer in staff chat.
    }
    return true;
  }
}
