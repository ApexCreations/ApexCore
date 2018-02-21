package me.savvy.api.modules.staffchat;

import me.savvy.api.commands.ApexCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCommand extends ApexCommand {

    public StaffChatCommand(String name) {
        super(name);

        setName("staffchat");
        setUsage(ChatColor.RED + "/sc <message>");
        setDescription(ChatColor.GREEN + "Use this for staff chat");
        setPermissionMessage(ChatColor.RED + "You do not have permission for this");
        
    }

    @Override
    public boolean executeCommand(CommandSender commandSender, String label, String[] args) {
        // blind casting because of this framework
        Player player = (Player) commandSender;
        
        if (!(args.length >= 1 )) {
            player.sendMessage(this.getUsage());
        }

        return true;
    }
}
