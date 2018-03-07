package io.apexcreations.core.main.requests;

import io.apexcreations.core.api.events.request.TeleportRequestHandler;
import org.bukkit.Location;

public class TeleportRequest implements TeleportRequestHandler {


  @Override
  public String getReason() {
    return null;
  }

  @Override
  public void setReason(String reason) {

  }

  @Override
  public String getMessage() {
    return null;
  }

  @Override
  public void setMessage(String message) {

  }

  @Override
  public boolean canTeleport() {
    return false;
  }

  @Override
  public Location getToLocation() {
    return null;
  }

  @Override
  public Location getFromLocation() {
    return null;
  }
}
