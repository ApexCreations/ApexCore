package io.apexcreations.core.modules.chat;


import io.apexcreations.core.ApexCore;
import io.apexcreations.core.modules.Module;
import io.apexcreations.core.modules.chat.commands.ClearChatCommand;
import org.bukkit.configuration.file.FileConfiguration;

public class ChatModule extends Module {


  private boolean chatSlowed, chatStopped;
  private int slowTime;

  public ChatModule(ApexCore apexCore, FileConfiguration config, String name, String description) {
    super(apexCore, config, name, description);
  }

  @Override
  public void initialize() {
    this.getPlugin().getCommandHandler().register(new ClearChatCommand(this.getPlugin(), "clearchat",
            "Clear global chat!",
            "apex.clearchat", false, "cc"));
  }

  @Override
  public void terminate() {
    this.getPlugin().getCommandHandler().unregister("clearchat");
  }

  @Override
  public void saveConfig() {

  }

  public boolean isChatStopped() {
    return chatStopped;
  }

  public void setChatStopped(boolean chatStopped) {
    this.chatStopped = chatStopped;
  }

  public boolean isChatSlowed() {
    return chatSlowed;
  }

  public void setChatSlowed(boolean chatSlowed) {
    this.chatSlowed = chatSlowed;
  }

  public int getSlowTime() {
    return slowTime;
  }

  public void setSlowTime(int slowTime) {
    this.slowTime = slowTime;
  }
}
