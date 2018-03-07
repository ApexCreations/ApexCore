package io.apexcreations.core.main.modules.staff;

import io.apexcreations.core.api.builders.MessageBuilder;
import io.apexcreations.core.api.commands.ApexCommand;
import io.apexcreations.core.api.players.ApexPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

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

        Optional<ApexPlayer> optionalPlayer = this.getAPI().getPlayerCache().get(player.getUniqueId());

        if (!optionalPlayer.isPresent()) {
            // Only way this is possible is if they weren't loaded on join
            MessageBuilder.create("&4Something went wrong, please relog.").withPrefix().send(player);
            return false;
        }

        ApexPlayer apexPlayer = optionalPlayer.get();

        apexPlayer.setStaffChat(!apexPlayer.isInStaffChat());

        if (apexPlayer.isInStaffChat()) {
            MessageBuilder.create("&eYou are now in staff chat").withPrefix().send(player);
            return true;
        }
        
        MessageBuilder.create("&eYou are no longer in staff chat").withPrefix().send(player);
        return true;
    }
}
