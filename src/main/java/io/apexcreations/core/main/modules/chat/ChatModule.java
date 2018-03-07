package io.apexcreations.core.main.modules.chat;

import me.savvy.api.modules.Module;

public class ChatModule extends Module {


  private boolean chatSlowed, chatStopped;
  private int slowTime;

  public ChatModule(String name, String description) {
    super(name, description);
  }

  @Override
  public void initialize() {
    this.generateConfig();
  }

  @Override
  public void terminate() {
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
