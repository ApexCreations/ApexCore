package io.apexcreations.core.api.cache;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class ApexSetCache<T> {
  
  private Set<T> setCache;

  public ApexSetCache() {
    this.setCache = new HashSet<>();
  }
  
  public void add(T object) {
    this.setCache.add(object);
  }
  
  public void remove(T object) {
    this.remove(obj -> obj.equals(object));
  }

  public void remove(Predicate<? super T> predicate) {
    this.getModule(predicate).ifPresent(this.setCache::remove);
  }
  
  public Optional<T> getModule(Predicate<? super T> predicate) {
    return this.setCache.stream().filter(predicate).findFirst();
  }
  
  public Set<T> getAllModules() {
    return Collections.unmodifiableSet(this.setCache);
  }
}
