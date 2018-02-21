package me.savvy.api.players;

import java.util.Map;
import java.util.UUID;
import me.savvy.main.account.Account;
import org.bukkit.Location;

public interface ApexPlayer {

  UUID getUniqueId();

  Account getAccount();

  Map<String, Location> getHomes();

  boolean isInStaffChat();

  void setStaffChat(boolean b);
}
