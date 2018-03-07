package io.apexcreations.core.main.cache;

import io.apexcreations.core.api.modules.Module;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ApexModuleCache {

  private Set<Module> apexModules;

  public ApexModuleCache() {
    this.apexModules = new HashSet<>();
  }

  /**
   * @param module - Register a Module.
   */
  public void register(Module module) {
    this.apexModules.add(module);
  }

  /**
   * @param module - Unregister a module from Apex if found
   */
  public void unregister(Module module) {
    this.apexModules.remove(module);
  }

  /**
   * @param moduleName - Search for a module by name and unregister it
   */
  public void unregister(String moduleName) {
    this.getModule(moduleName).ifPresent(this.apexModules::remove);
  }

  /**
   * @param moduleName - Name of the module you're searching for
   * @return - Optional Module
   */
  public Optional<Module> getModule(String moduleName) {
    return this.apexModules.stream().filter(module ->
        module.getName().equalsIgnoreCase(moduleName)).findFirst();
  }

  /**
   * @return A unmodifiable collection of all registered Apex Modules (May or may not be enabled.)
   */
  public Set<Module> getAllModules() {
    return Collections.unmodifiableSet(this.apexModules);
  }
}
