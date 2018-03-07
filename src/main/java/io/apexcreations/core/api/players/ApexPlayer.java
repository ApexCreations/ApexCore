package io.apexcreations.core.api.players;

import io.apexcreations.core.main.account.Account;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Location;

public interface ApexPlayer {

  UUID getUniqueId();

  Account getAccount();

  Map<String, Location> getHomes();

  boolean isInStaffChat();

  void setStaffChat(boolean b);
}
