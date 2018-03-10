package io.apexcreations.core.players;

import java.util.Map;
import java.util.UUID;

import io.apexcreations.core.modules.economy.account.Account;
import org.bukkit.Location;

public interface ApexPlayer {

  UUID getUniqueId();

  Account getAccount();

  Map<String, Location> getHomes();

  boolean isInStaffChat();

  void setStaffChat(boolean b);
}
