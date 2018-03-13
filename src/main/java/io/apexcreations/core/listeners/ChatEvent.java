package io.apexcreations.core.listeners;

import io.apexcreations.core.ApexCore;
import org.bukkit.event.Listener;

public class ChatEvent implements Listener {

    // TODO: This class can be used for monitoring muted chat, slowed chat etc

    private ApexCore apexCore;

    public ChatEvent(ApexCore apexCore) {
        this.apexCore = apexCore;
    }

}
