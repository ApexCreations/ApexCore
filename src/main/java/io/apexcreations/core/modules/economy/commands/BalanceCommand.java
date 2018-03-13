package io.apexcreations.core.modules.economy.commands;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.builders.MessageBuilder;
import io.apexcreations.core.commands.ApexCommand;
import io.apexcreations.core.modules.Module;
import io.apexcreations.core.modules.economy.EconomyModule;
import io.apexcreations.core.modules.economy.account.Account;
import io.apexcreations.core.modules.economy.commands.sub.BalancePlayerSubCommand;
import io.apexcreations.core.players.ApexPlayer;
import io.apexcreations.core.utils.Utils;
import java.util.Optional;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BalanceCommand extends ApexCommand {

    public BalanceCommand(ApexCore apexCore, String name, String description, String permission,
            boolean playerOnly,
            String... aliases) {
        super(apexCore, name, description, permission, playerOnly, aliases);
        this.getApexCore().getSubCommandCache().add("other",
                new BalancePlayerSubCommand(apexCore, "other", "Check the balance of a player's account!",
                        "apex.balance.other", false));
    }

    @Override
    public boolean executeCommand(CommandSender commandSender, String label, String[] args) {

        Player player = (Player) commandSender;

        Optional<ApexPlayer> optionalApexPlayer = this.getApexCore().getPlayerCache()
                .get(player.getUniqueId());

        if (!optionalApexPlayer.isPresent()) {
            MessageBuilder.create("&cCould not find player data!").withPrefix(PREFIX)
                    .send(commandSender);
            return false;
        }

        ApexPlayer apexPlayer = optionalApexPlayer.get();

        if (apexPlayer.getAccount() == null) {
            MessageBuilder.create("&cCould not find player account!").withPrefix(PREFIX)
                    .send(commandSender);
            return false;
        }

        Account account = apexPlayer.getAccount();
        Optional<Module> optionalEconomyModule =
                this.getApexCore().getModuleManager().getModuleCache().get("Economy");
        if (!optionalEconomyModule.isPresent()) {
            // Do something
            return false;
        }
        EconomyModule economyModule = (EconomyModule) optionalEconomyModule.get();
        String currency = economyModule.getCurrencySymbol();
        MessageBuilder
                .create("&a&lBALANCE &7&l>> &a&l" + currency + Utils
                        .formatCurrency(account.getBalance()))
                .send(commandSender);

        return false;
    }
}
