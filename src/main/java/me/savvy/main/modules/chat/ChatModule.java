package me.savvy.main.modules.chat;

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
}
