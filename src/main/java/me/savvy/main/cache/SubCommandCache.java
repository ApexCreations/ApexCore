package me.savvy.main.cache;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import me.savvy.api.commands.SubCommand;

public class SubCommandCache {

  private static SubCommandCache instance;
  private Set<SubCommand> subCommandSet;

  private SubCommandCache() {
    instance = this;
    this.subCommandSet = new HashSet<>();
  }

  public void add(SubCommand subCommand) {
    this.subCommandSet.add(subCommand);
  }

  public void remove(SubCommand subCommand) {
    this.subCommandSet.remove(subCommand);
  }

  public static SubCommandCache getInstance() {
    return instance == null ? new SubCommandCache() : instance;
  }

  public Optional<SubCommand> search(String args) {
    return this.subCommandSet.stream().filter(subCommand ->
        subCommand.getName().equalsIgnoreCase(args) ||
            Arrays.stream(subCommand.getAliases())
                .anyMatch(string -> string.equalsIgnoreCase(args))).findFirst();
  }
}