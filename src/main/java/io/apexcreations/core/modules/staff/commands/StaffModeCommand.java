package io.apexcreations.core.modules.staff.commands;

import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import io.apexcreations.core.players.ApexPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class StaffModeCommand extends ApexCommand {
    
    public StaffModeCommand(String name, String description, String permission, boolean playerOnly, String... aliases) {
        super(name, description, permission, playerOnly, aliases);
    }
    
    @Override
    public boolean executeCommand(CommandSender commandSender, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            MessageBuilder.create("This is for player's only!").send(commandSender);
            return true;
        }
        Player player = (Player) commandSender;
        
        Optional<ApexPlayer> optionalPLayer = this.getApexCore().getPlayerCache().get(player.getUniqueId());
        
        if (!optionalPLayer.isPresent()) return false;
        
        ApexPlayer apexPlayer = optionalPLayer.get();
        
        apexPlayer.setStaffMode(!apexPlayer.isInStaffMode());
        
        String staement = ChatColor.YELLOW + "You have toggled staff mode " + (apexPlayer.isInStaffMode() ? ChatColor.GREEN + "ON" : ChatColor.RED + "OFF");
        
        MessageBuilder.create(staement).send(player);
        
        return true;
    }
}
