package io.apexcreations.core.api.events.request;

import org.bukkit.Location;

public interface TeleportRequestHandler {

  /**
   * Reason why player can not teleport
   *
   * @return {@link String}
   */
  String getReason();

  /**
   * Set the reason why a player can not teleport to spawn
   */
  void setReason(String reason);

  /**
   * Message to send on teleportation success
   *
   * @return {@link String}
   */
  String getMessage();

  /**
   * Set the message to be sent on teleportation success
   */
  void setMessage(String message);

  /**
   * Check if a player is allowed to teleport to this {@link Location}.
   *
   * @return boolean
   */
  boolean canTeleport();

  /**
   * Get the location player is requesting to teleport to.
   *
   * @return {@link Location}
   */
  Location getToLocation();

  /**
   * Get the location of player before/during teleport
   *
   * @return {@link Location}
   */
  Location getFromLocation();


}
