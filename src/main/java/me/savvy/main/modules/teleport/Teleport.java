package me.savvy.main.modules.teleport;

import me.savvy.api.builders.MessageBuilder;
import me.savvy.api.commands.ApexCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
Fancy command made by me :3
    - could prob be made better
 */

public class Teleport extends ApexCommand {

    public Teleport (String name, String description, String permission, boolean playerOnly, String... aliases) {
        super(name, description, permission, playerOnly, aliases);
        this.setUsage(ChatColor.RED + "/" + this.getLabel() + " <player>");

    }

    @Override
    public boolean executeCommand(CommandSender commandSender, String label, String[] args) {
        Player player = (Player) commandSender;

        if (!(args.length >= 1)) {
            MessageBuilder.create(this.getUsage()).send(player);
            return false;
        }
        Player target = Bukkit.getPlayer(args[1]);
        Location loc = target.getPlayer().getLocation();
        loc.setY(loc.getY() - 2);

            if(!target.isOnline()) {
                MessageBuilder.create("Player can not be found!").send(player);
            }else if(target.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty()) {
                MessageBuilder.create(String.format("%s is standing on a unsafe location, please try again later", target.getPlayer().getName())).send(player);
            }else {
                player.teleport(target);
            }
            return true;
    }
}
