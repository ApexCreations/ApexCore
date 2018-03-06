package me.savvy.api.cache;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ApexSetCache<T> {
  
  private Set<T> setCache;

  public ApexSetCache() {
    this.setCache = new HashSet<>();
  }
  
  public void add(T object) {
    this.setCache.add(object);
  }
  
  public void remove(T object) {
    this.remove(object.toString());
  }

  public void remove(String string) {
    this.getModule(string).ifPresent(this.setCache::remove);
  }
  
  public Optional<T> getModule(String string) {
    return this.setCache.stream().filter(module ->
        module.toString().equalsIgnoreCase(string)).findFirst();
  }
  
  public Set<T> getAllModules() {
    return Collections.unmodifiableSet(this.setCache);
  }
}
