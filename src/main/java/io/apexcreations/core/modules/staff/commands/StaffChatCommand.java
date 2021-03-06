package io.apexcreations.core.modules.staff.commands;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import io.apexcreations.core.players.ApexPlayer;
import java.util.Optional;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand extends ApexCommand {

  public StaffChatCommand(ApexCore apexCore, String name, String description, String permission, boolean playerOnly,
      String... aliases) {
    super(apexCore, name, description, permission, playerOnly, aliases);
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

    Optional<ApexPlayer> optionalPlayer = this.getApexCore().getPlayerCache().get(player.getUniqueId());

    if (!optionalPlayer.isPresent()) {
      // Only way this is possible is if they weren't loaded on join
      MessageBuilder.create("&4Something went wrong, please relog.").withPrefix(PREFIX).send(player);
      return false;
    }

    ApexPlayer apexPlayer = optionalPlayer.get();

    apexPlayer.setStaffChat(!apexPlayer.isInStaffChat());

    if (apexPlayer.isInStaffChat()) {
      MessageBuilder.create("&eYou are now in staff chat").withPrefix(PREFIX).send(player);
      return true;
    }

    MessageBuilder.create("&eYou are no longer in staff chat").withPrefix(PREFIX).send(player);
    return true;
  }
}
