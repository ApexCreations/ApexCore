package me.savvy.main.cache;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import me.savvy.api.modules.Module;

public class ApexModuleCache {

  private Set<Module> apexModules;

  public ApexModuleCache() {
    this.apexModules = new HashSet<>();
  }

  public void register(Module module) {
    this.apexModules.add(module);
  }

  public void unregister(Module module) {
    this.apexModules.remove(module);
  }

  public void unregister(String moduleName) {
    this.getModule(moduleName).ifPresent(this.apexModules::remove);
  }

  public Optional<Module> getModule(String moduleName) {
    return this.apexModules.stream().filter(module ->
        module.getName().equalsIgnoreCase(moduleName)).findFirst();
  }

  public Set<Module> getAllModules() {
    return this.apexModules;
  }
}
