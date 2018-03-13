package io.apexcreations.core.modules.staff;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.players.ApexPlayer;
import java.util.Optional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ChatListener implements Listener {

    private ApexCore apexCore;

    public ChatListener(ApexCore apexCore) {
        this.apexCore = apexCore;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        // This specific event can be moved under staff chat module.
        Optional<ApexPlayer> optionalPlayer = this.apexCore.getPlayerCache()
                .get(player.getUniqueId());

        if (!optionalPlayer.isPresent()) {
            // Player can not be found please handle this.
            return;
        }

        ApexPlayer apexPlayer = optionalPlayer.get();
        // This can be easily cleaned. Will do at later date
        if (apexPlayer.isInStaffChat()) {
            for (Player recipient : event.getRecipients()) {
                Optional<ApexPlayer> optionalApexPlayer = this.apexCore.getPlayerCache()
                        .get(recipient.getUniqueId());
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
