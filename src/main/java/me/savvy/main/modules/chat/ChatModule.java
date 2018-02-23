package me.savvy.main.modules.chat;

import me.savvy.api.modules.Module;

public class ChatModule extends Module {

  public ChatModule(String name, String description) {
    super(name, description);
  }

  @Override
  public void initialize() {
    if (!this.isEnabled()) {
      this.setEnabled(true);
    }
  }

  @Override
  public void terminate() {
    if (this.isEnabled()) {
      this.setEnabled(false);
    }
  }
}
