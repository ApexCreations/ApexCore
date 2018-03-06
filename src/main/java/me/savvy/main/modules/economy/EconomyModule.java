package me.savvy.main.modules.economy;

import me.savvy.api.economy.ApexEconomy;
import me.savvy.api.modules.Module;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

public class EconomyModule extends Module {

  private ApexEconomy apexEconomy;

  public EconomyModule(String name, String description) {
    super(name, description);
  }

  @Override
  public void initialize() {
    this.registerVault();
    ;
  }

  @Override
  public void terminate() {
    this.unregisterVault();
  }

  private void registerVault() {
    if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
      this.apexEconomy = new ApexEconomy(this.getPlugin());
      Bukkit.getServer().getServicesManager()
          .register(Economy.class, this.apexEconomy, this.getPlugin(), ServicePriority.Highest);
    } else {
      getLogger().severe("Could not find vault, avoiding economy registration");
      this.getPlugin().getApexAPI().getApexConfigCache().setEconomyEnabled(false);
    }
  }

  private void unregisterVault() {
    if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null) {
      Bukkit.getServer().getServicesManager().unregister(Economy.class, this.apexEconomy);
    }
  }
}
