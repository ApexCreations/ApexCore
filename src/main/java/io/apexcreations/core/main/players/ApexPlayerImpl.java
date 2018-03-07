package io.apexcreations.core.main.players;

import io.apexcreations.core.api.players.ApexPlayer;
import io.apexcreations.core.main.account.Account;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Location;

public class ApexPlayerImpl implements ApexPlayer {

  private final UUID uniqueId;
  private final Account account;
  private final Map<String, Location> homes;
  private boolean staffChat;

  public ApexPlayerImpl(UUID uuid) {
    this.uniqueId = uuid;
    this.account = new Account(this.uniqueId);
    this.homes = new HashMap<>();
  }

  @Override
  public Account getAccount() {
    return this.account;
  }

  @Override
  public Map<String, Location> getHomes() {
    return Collections.unmodifiableMap(this.homes);
  }

  @Override
  public UUID getUniqueId() {
    return this.uniqueId;
  }

  @Override
  public boolean isInStaffChat() {
    return this.staffChat;
  }

  @Override
  public void setStaffChat(boolean staffChat) {
    this.staffChat = staffChat;
  }
}
