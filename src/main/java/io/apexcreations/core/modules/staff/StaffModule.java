package io.apexcreations.core.modules.staff;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.modules.Module;
import io.apexcreations.core.modules.staff.commands.StaffChatCommand;
import io.apexcreations.core.modules.staff.commands.StaffModeCommand;
import org.bukkit.configuration.file.FileConfiguration;

public class StaffModule extends Module {

    public StaffModule(ApexCore apexCore, FileConfiguration config, String name,
            String description) {
        super(apexCore, config, name, description);
    }

    @Override
    public void initialize() {
        this.getPlugin().getCommandHandler().register(
                new StaffChatCommand(this.getPlugin(), "staffchat",
                        "Use this for staff chat",
                        "apex.staffchat", true, "sc"),

                new StaffModeCommand(this.getPlugin(), "staffmode",
                        "Toggles your staff mode",
                        "apex.staffmode", true, "sm")
        );
    }

    @Override
    public void terminate() {
        this.getPlugin().getCommandHandler().unregister("staffchat", "staffmode");
    }

    @Override
    public void saveConfig() {

    }
}
