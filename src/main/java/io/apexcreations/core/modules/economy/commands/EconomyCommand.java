package io.apexcreations.core.modules.economy.commands;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.commands.ApexCommand;
import io.apexcreations.core.modules.economy.commands.sub.EconomyGiveSubCommand;
import io.apexcreations.core.modules.economy.commands.sub.EconomyTakeSubCommand;
import org.bukkit.command.CommandSender;

public class EconomyCommand extends ApexCommand {

    public EconomyCommand(ApexCore apexCore, String name, String description, String permission,
            boolean playerOnly,
            String... aliases) {
        super(apexCore, name, description, permission, playerOnly, aliases);
        this.getSubCommandCache().add("give",
                new EconomyGiveSubCommand(apexCore, "give", "Deposit money into a player's account!",
                        "apex.eco.give", false));
        this.getSubCommandCache().add("take",
                new EconomyTakeSubCommand(apexCore, "take", "Withdraw money from a player's account!",
                        "apex.eco.take", false));
    }

    @Override
    public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

        // Send help message;
        return false;
    }
}
