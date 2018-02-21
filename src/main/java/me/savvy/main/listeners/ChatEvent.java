package me.savvy.main.listeners;

import java.util.Optional;
import me.savvy.api.listeners.ApexListener;
import me.savvy.api.players.ApexPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent extends ApexListener implements Listener {

  @EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    Player player = event.getPlayer();

    // This specific event can be moved under staff chat module.
    Optional<ApexPlayer> optionalPlayer = this.getPlayerCache().get(player);

    if (!optionalPlayer.isPresent()) {
      // Player can not be found please handle this.
      return;
    }

    ApexPlayer apexPlayer = optionalPlayer.get();
    // This can be easily cleaned. Will do at later date
    if (apexPlayer.isInStaffChat()) {
      for (Player recipient : event.getRecipients()) {
        Optional<ApexPlayer> optionalApexPlayer = this.getPlayerCache().get(recipient);
        if (optionalApexPlayer.isPresent()) {
          ApexPlayer apex = optionalApexPlayer.get();
          if (!apex.isInStaffChat()) {
            event.getRecipients().remove(recipient);
          }
        } else {
          event.getRecipients().remove(recipient);
        }
      }
      return;
    }

  }

}
