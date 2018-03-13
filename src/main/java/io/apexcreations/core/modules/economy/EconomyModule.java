package io.apexcreations.core.modules.economy;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.modules.Module;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;

public class EconomyModule extends Module {

    private String currencySymbol, economyName, currencyNameSingular, currencyNamePlural;
    private int maxBalance, minBalance, defaultBalance;
    private ApexEconomy apexEconomy;


    public EconomyModule(ApexCore apexCore, FileConfiguration config, String name, String description) {
        super(apexCore, config, name, description);
    }

    @Override
    public void initialize() {
        if (!this.isEnabled()) {
            return;
        }
        if (this.getConfig().isSet("economy.defaultBalance")) {
            this.defaultBalance = this.getConfig().getInt("economy.defaultBalance", 1000);
        }
        this.economyName = this.getConfig().getString("economy.name", "Apex");
        this.currencySymbol = this.getConfig().getString("economy.currencySymbol", "$");
        this.currencyNameSingular = this.getConfig().getString("economy.singularName", "Dollar");
        this.currencyNamePlural = this.getConfig().getString("economy.pluralName", "Dollars");
        this.minBalance = this.getConfig().getInt("economy.minBalance", 0);
        this.maxBalance = this.getConfig().getInt("economy.maxBalance", 10000000);
        this.registerVault();
    }

    @Override
    public void terminate() {
        this.saveConfig();
        this.unregisterVault();
    }

    @Override
    public void saveConfig() {
        this.getConfig().set(this.getSimpleName() + ".enabled", this.isEnabled());
        this.getConfig().set(this.getSimpleName() + ".name", this.economyName);
        this.getConfig().set(this.getSimpleName() + ".currencySymbol", this.currencySymbol);
        this.getConfig().set(this.getSimpleName() + ".singularName", this.currencyNameSingular);
        this.getConfig().set(this.getSimpleName() + ".pluralName", this.currencyNamePlural);
        this.getConfig().set(this.getSimpleName() + ".defaultBalance", this.defaultBalance);
        this.getConfig().set(this.getSimpleName() + ".minBalance", this.minBalance);
        this.getConfig().set(this.getSimpleName() + ".maxBalance", this.maxBalance);
    }

    private void registerVault() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            this.apexEconomy = new ApexEconomy(this.getPlugin(), this);
            Bukkit.getServer().getServicesManager()
                    .register(Economy.class, this.apexEconomy, this.getPlugin(),
                            ServicePriority.Highest);
        } else {
            getLogger().severe("Could not find vault, avoiding economy registration");
            this.setEnabled(false);
        }
    }

    private void unregisterVault() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            Bukkit.getServer().getServicesManager().unregister(Economy.class, this.apexEconomy);
        }
    }

    public int getMaxBalance() {
        return this.maxBalance;
    }

    public int getMinBalance() {
        return this.minBalance;
    }

    public int getDefaultBalance() {
        return this.defaultBalance;
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public String getEconomyName() {
        return this.economyName;
    }

    public String getCurrencyNameSingular() {
        return this.currencyNameSingular;
    }

    public String getCurrencyNamePlural() {
        return this.currencyNamePlural;
    }
}
