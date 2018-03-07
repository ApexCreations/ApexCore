package io.apexcreations.core.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerAccountLoadEvent extends Event {

  private final HandlerList handlerList = new HandlerList();

  @Override
  public HandlerList getHandlers() {
    return handlerList;
  }
}