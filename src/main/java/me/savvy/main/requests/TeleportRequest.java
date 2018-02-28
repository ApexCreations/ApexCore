package me.savvy.main.requests;

import me.savvy.api.events.request.TeleportRequestHandler;
import org.bukkit.Location;

public class TeleportRequest implements TeleportRequestHandler {
    
    
    @Override
    public String getReason() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public void setReason(String reason) {

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
