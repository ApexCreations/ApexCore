package io.apexcreations.core.api.economy;

import com.google.inject.Inject;
import io.apexcreations.core.ApexAPI;
import io.apexcreations.core.ApexCore;
import io.apexcreations.core.api.exceptions.MaxMoneyException;
import io.apexcreations.core.api.players.ApexPlayer;
import io.apexcreations.core.main.account.Account;
import io.apexcreations.core.main.players.ApexPlayerImpl;
import io.apexcreations.core.main.utils.Utils;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.economy.EconomyResponse.ResponseType;
import org.bukkit.OfflinePlayer;

/**
 * Please avoid using any of the deprecated methods as they are <b>NOT</b> implemented.
 */
public class ApexEconomy implements Economy {

  @Inject
  private ApexCore apexCore;
  private final ApexAPI apexAPI;

  public ApexEconomy() {
    this.apexAPI = this.apexCore.getApexAPI();
  }

  @Override
  public boolean isEnabled() {
    return this.apexAPI.getApexConfigCache().isEconomyEnabled();
  }

  @Override
  public String getName() {
    return this.apexAPI.getApexConfigCache().getEconomyName();
  }

  @Override
  public boolean hasBankSupport() {
    return false;
  }

  @Override
  public int fractionalDigits() {
    return -1;
  }

  @Override
  public String format(double v) {
    return Utils.formatCurrency(BigDecimal.valueOf(v));
  }

  @Override
  public String currencyNameSingular() {
    return this.apexAPI.getApexConfigCache().getCurrencyNameSingular();
  }

  @Override
  public String currencyNamePlural() {
    return this.apexAPI.getApexConfigCache().getCurrencyNamePlural();
  }

  @Override
  public boolean hasAccount(OfflinePlayer offlinePlayer) {
    return this.apexAPI.getPlayerCache().get(offlinePlayer.getUniqueId())
        .filter(apexPlayer -> apexPlayer.getAccount() != null).isPresent();
  }

  @Override
  public boolean hasAccount(OfflinePlayer offlinePlayer, String worldName) {
    return this.apexAPI.getPlayerCache().get(offlinePlayer.getUniqueId())
        .filter(apexPlayer -> apexPlayer.getAccount() != null).isPresent();
  }

  @Override
  public double getBalance(OfflinePlayer offlinePlayer) {
    return this.apexAPI.getPlayerCache().get(offlinePlayer.getUniqueId())
        .map(apexPlayer -> apexPlayer.getAccount().getBalance().doubleValue()).orElse(0.0);
  }

  @Override
  public double getBalance(OfflinePlayer offlinePlayer, String worldName) {
    return getBalance(offlinePlayer);
  }

  @Override
  public boolean has(OfflinePlayer offlinePlayer, double amount) {
    return this.apexAPI.getPlayerCache().get(offlinePlayer.getUniqueId())
        .map(apexPlayer -> apexPlayer.getAccount().getBalance().doubleValue() >= amount)
        .orElse(false);
  }

  @Override
  public boolean has(OfflinePlayer offlinePlayer, String worldName, double amount) {
    return has(offlinePlayer, amount);
  }

  @Override
  public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
    Optional<ApexPlayer> optionalPlayer = this.apexAPI.getPlayerCache().get(player.getUniqueId());

    if (!optionalPlayer.isPresent()) {
      return new EconomyResponse(amount, 0.0, EconomyResponse.ResponseType.FAILURE,
          "Could not find player account");
    }
    Account account = optionalPlayer.get().getAccount();
    EconomyResponse response;
    if (!this.has(player, amount)) {
      response = new EconomyResponse(amount, account.getBalance().doubleValue(),
          EconomyResponse.ResponseType.FAILURE,
          "You do not have enough funds for this transaction!");
    } else {
      try {
        account.removeFromBalance(amount);
        response = new EconomyResponse(amount, account.getBalance().doubleValue(),
            EconomyResponse.ResponseType.SUCCESS, "");
      } catch (MaxMoneyException e) {
        response = new EconomyResponse(amount, account.getBalance().doubleValue(),
            EconomyResponse.ResponseType.FAILURE,
            e.getMessage());
      }
    }
    return response;
  }

  @Override
  public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
    return withdrawPlayer(player, amount);
  }

  @Override
  public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
    Optional<ApexPlayer> optionalPlayer = this.apexAPI.getPlayerCache().get(player.getUniqueId());

    if (!optionalPlayer.isPresent()) {
      return new EconomyResponse(amount, 0.0, EconomyResponse.ResponseType.FAILURE,
          "Could not find player account");
    }
    Account account = optionalPlayer.get().getAccount();
    EconomyResponse response;
    try {
      account.addToBalance(amount);
      response = new EconomyResponse(amount, account.getBalance().doubleValue(),
          EconomyResponse.ResponseType.SUCCESS, "");
    } catch (MaxMoneyException e) {
      response = new EconomyResponse(amount, account.getBalance().doubleValue(),
          ResponseType.FAILURE, e.getMessage());
    }
    return response;
  }

  @Override
  public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
    return depositPlayer(player, amount);
  }

  @Override
  public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
    if (!offlinePlayer.isOnline()) {
      return false;
    }
    Optional<ApexPlayer> optionalPlayer = this.apexAPI.getPlayerCache()
        .get(offlinePlayer.getUniqueId());

    if (!optionalPlayer.isPresent()) {
      this.apexAPI.getPlayerCache()
          .add(offlinePlayer.getUniqueId(), new ApexPlayerImpl(offlinePlayer.getUniqueId()));
    }
    return true;
  }

  @Override
  public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
    return createPlayerAccount(player);
  }

  @Deprecated
  @Override
  public boolean createPlayerAccount(String playerName) {
    return false;
  }

  @Deprecated
  @Override
  public boolean createPlayerAccount(String playerName, String worldName) {
    return false;
  }

  @Deprecated
  @Override
  public boolean hasAccount(String playerName) {
    return false;
  }

  @Override
  public EconomyResponse createBank(String s, String s1) {
    return null;
  }

  @Override
  public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
    return null;
  }

  @Override
  public EconomyResponse deleteBank(String s) {
    return null;
  }

  @Override
  public EconomyResponse bankBalance(String s) {
    return null;
  }

  @Override
  public EconomyResponse bankHas(String s, double v) {
    return null;
  }

  @Override
  public EconomyResponse bankWithdraw(String s, double v) {
    return null;
  }

  @Override
  public EconomyResponse bankDeposit(String s, double v) {
    return null;
  }

  @Override
  public EconomyResponse isBankOwner(String s, String s1) {
    return null;
  }

  @Override
  public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
    return null;
  }

  @Override
  public EconomyResponse isBankMember(String s, String s1) {
    return null;
  }

  @Override
  public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
    return null;
  }

  @Override
  public List<String> getBanks() {
    return null;
  }

  @Deprecated
  @Override
  public boolean hasAccount(String playerName, String worldName) {
    return false;
  }

  @Deprecated
  @Override
  public double getBalance(String playerName) {
    return 0;
  }

  @Deprecated
  @Override
  public double getBalance(String playerName, String worldName) {
    return 0;
  }

  @Deprecated
  @Override
  public boolean has(String playerName, double amount) {
    return false;
  }

  @Deprecated
  @Override
  public boolean has(String playerName, String worldName, double amount) {
    return false;
  }

  @Deprecated
  @Override
  public EconomyResponse withdrawPlayer(String playerName, double amount) {
    return null;
  }

  @Deprecated
  @Override
  public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
    return null;
  }

  @Deprecated
  @Override
  public EconomyResponse depositPlayer(String playerName, double amount) {
    return null;
  }

  @Deprecated
  @Override
  public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
    return null;
  }
}
