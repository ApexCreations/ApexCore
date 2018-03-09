package io.apexcreations.core.main.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ApexMapCache<K, V> {

  private final Map<K, V> cacheMap;

  public ApexMapCache(boolean concurrent) {
    this.cacheMap = (concurrent) ? new ConcurrentHashMap<>() : new HashMap<>();
  }

  public Optional<V> get(K key) {
    if (!this.has(key)) {
      return Optional.empty();
    }
    return Optional.of(this.cacheMap.get(key));
  }

  public boolean has(K key) {
    return this.cacheMap.containsKey(key);
  }

  public void add(K key, V value) {
    if (this.has(key)) {
      return;
    }
    this.cacheMap.put(key, value);
  }

  public void remove(K key) {
    if (!this.has(key)) {
      return;
    }
    this.cacheMap.remove(key);
  }

  public Map<K, V> getMap() {
    return Collections.unmodifiableMap(this.cacheMap);
  }
}