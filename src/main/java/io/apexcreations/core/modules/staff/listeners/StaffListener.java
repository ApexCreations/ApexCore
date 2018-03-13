package io.apexcreations.core.modules.staff.listeners;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.players.ApexPlayer;
import java.util.Optional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class StaffListener implements Listener {

    private ApexCore apexCore;

    public StaffListener(ApexCore apexCore) {
        this.apexCore = apexCore;
    }

    @EventHandler
    public void onStaffInteract(PlayerInteractEvent event) {
        Optional<ApexPlayer> optionPlayer = this.apexCore.getPlayerCache()
                .get(event.getPlayer().getUniqueId());

        if (!optionPlayer.isPresent()) {
            return;
        }

        ApexPlayer player = optionPlayer.get();

        // Will expand more, this is just the basic idea
        if (player.isInStaffMode()) {
            event.setCancelled(true);
        }
    }
}
