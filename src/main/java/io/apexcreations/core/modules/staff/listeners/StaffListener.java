package io.apexcreations.core.modules.staff.listeners;

import io.apexcreations.core.listeners.ApexListener;
import io.apexcreations.core.players.ApexPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Optional;

public class StaffListener extends ApexListener implements Listener {
    
    
    
    
    @EventHandler
    public void onStaffInteract(PlayerInteractEvent event) {
        Optional<ApexPlayer> optionPlayer = getPlayerCache().get(event.getPlayer().getUniqueId());
        
        if (!optionPlayer.isPresent()) return;
        
        ApexPlayer player = optionPlayer.get();
        
        // Will expand more, this is just the basic idea
        if (player.isInStaffMode()) {
            event.setCancelled(true);
        }
    }
}
