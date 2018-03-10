package io.apexcreations.core.modules.economy;

import io.apexcreations.core.modules.Module;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.ServicePriority;

public class EconomyModule extends Module {

    private ApexEconomy apexEconomy;
    private int maxBalance, minBalance, defaultBalance;
    private String currencySymbol, economyName, currencyNameSingular, currencyNamePlural;
    private boolean economyEnabled;

    public EconomyModule(String name, String description) {
        super(name, description);
    }

    @Override
    public void initialize() {
        this.registerVault();
    }
    
    @Override
    public void terminate() {
        this.unregisterVault();
    }

    @Override
    public void loadConfig(FileConfiguration config) {
        if (config.isSet("economy.defaultBalance")) {
            this.defaultBalance = config.getInt("economy.defaultBalance", 1000);
        }
        this.economyName = config.getString("economy.name", "Apex");
        this.economyEnabled = config.getBoolean("economy.enabled", true);
        this.currencySymbol = config.getString("economy.currencySymbol", "$");
        this.currencyNameSingular = config.getString("economy.singularName", "Dollar");
        this.currencyNamePlural = config.getString("economy.pluralName", "Dollars");
        this.minBalance = config.getInt("economy.minBalance", 0);
        this.maxBalance = config.getInt("economy.maxBalance", 10000000);
    }

    @Override
    public void saveConfig(FileConfiguration config) {
        config.set("economy.name", this.economyName);
        config.set("economy.enabled", this.economyEnabled);
        config.set("economy.currencySymbol", this.currencySymbol);
        config.set("economy.singularName", this.currencyNameSingular);
        config.set("economy.pluralName", this.currencyNamePlural);
        config.set("economy.defaultBalance", this.defaultBalance);
        config.set("economy.minBalance", this.minBalance);
        config.set("economy.maxBalance", this.maxBalance);
    }

    private void registerVault() {
        if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
            this.apexEconomy = new ApexEconomy(this);
            Bukkit.getServer().getServicesManager()
                    .register(Economy.class, this.apexEconomy, this.getPlugin(),
                            ServicePriority.Highest);
        } else {
            getLogger().severe("Could not find vault, avoiding economy registration");
            this.economyEnabled = false;
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

    public boolean isEconomyEnabled() {
        return this.economyEnabled;
    }
}
