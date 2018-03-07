package io.apexcreations.core.main.modules.staff;

import io.apexcreations.core.api.listeners.ApexListener;

import io.apexcreations.core.api.players.ApexPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Optional;


public class ChatListener extends ApexListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        // This specific event can be moved under staff chat module.
        Optional<ApexPlayer> optionalPlayer = this.getPlayerCache().get(player.getUniqueId());

        if (!optionalPlayer.isPresent()) {
            // Player can not be found please handle this.
            return;
        }

        ApexPlayer apexPlayer = optionalPlayer.get();
        // This can be easily cleaned. Will do at later date
        if (apexPlayer.isInStaffChat()) {
            for (Player recipient : event.getRecipients()) {
                Optional<ApexPlayer> optionalApexPlayer = this.getPlayerCache().get(recipient.getUniqueId());
                if (optionalApexPlayer.isPresent()) {
                    ApexPlayer apex = optionalApexPlayer.get();
                    if (!apex.isInStaffChat()) {
                        event.getRecipients().remove(recipient);
                    }
                } else {
                    event.getRecipients().remove(recipient);
                }
            }
        }

    }
}
