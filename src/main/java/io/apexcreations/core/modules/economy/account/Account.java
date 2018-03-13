package io.apexcreations.core.modules.economy.account;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.exceptions.MaxMoneyException;
import io.apexcreations.core.modules.Module;
import io.apexcreations.core.modules.economy.EconomyModule;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public class Account {

    private final UUID accountOwner;
    private BigDecimal balance;
    private EconomyModule economyModule;
    private ApexCore apexCore;

    public Account(ApexCore apexCore, UUID accountOwner) {
        this.apexCore = apexCore;
        this.accountOwner = accountOwner;
        this.load();
    }

    private void load() {
        Optional<Module> optionalEconomyModule = this.apexCore.
                getModuleManager().getModuleCache().get("Economy");
        if (!optionalEconomyModule.isPresent()) {
            // Do something
            return;
        }
        this.economyModule = (EconomyModule) optionalEconomyModule.get();
        this.balance = BigDecimal.ZERO;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) throws MaxMoneyException {
        if ((balance.doubleValue() + this.balance.doubleValue()) >
                this.economyModule.getMaxBalance() ||
                (balance.doubleValue() - this.balance.doubleValue()) <
                        this.economyModule.getMinBalance()) {
            throw new MaxMoneyException();
        }
        this.balance = balance;
    }

    public void addToBalance(double amount) throws MaxMoneyException {
        this.setBalance(this.balance.add(this.toBigDecimal(amount)));
    }

    public void removeFromBalance(double amount) throws MaxMoneyException {
        this.setBalance(this.balance.subtract(this.toBigDecimal(amount)));
    }

    public UUID getAccountOwner() {
        return this.accountOwner;
    }

    private BigDecimal toBigDecimal(double amount) {
        return BigDecimal.valueOf(amount);
    }
}
