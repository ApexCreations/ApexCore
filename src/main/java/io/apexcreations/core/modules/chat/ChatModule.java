package io.apexcreations.core.modules.chat;


import io.apexcreations.core.modules.Module;
import org.bukkit.configuration.file.FileConfiguration;

public class ChatModule extends Module {


  private boolean chatSlowed, chatStopped;
  private int slowTime;

  public ChatModule(String name, String description) {
    super(name, description);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void terminate() {
  }

  @Override
  public void loadConfig(FileConfiguration config) {

  }

  @Override
  public void saveConfig(FileConfiguration config) {

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
