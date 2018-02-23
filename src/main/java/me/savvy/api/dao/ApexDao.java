package me.savvy.api.dao;

import java.util.Optional;
import java.util.UUID;
import me.savvy.api.players.ApexPlayer;

public interface ApexDao {

  void insert(ApexPlayer apexPlayer);

  void save(ApexPlayer apexPlayer);

  void saveSync(ApexPlayer apexPlayer);

  void delete(ApexPlayer apexPlayer);

  void delete(UUID uuid);

  Optional<ApexPlayer> findByUniqueId(UUID uniqueId);

}
