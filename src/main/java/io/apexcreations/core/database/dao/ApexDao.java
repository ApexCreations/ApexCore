package io.apexcreations.core.main.database.dao;

import io.apexcreations.core.players.ApexPlayer;
import java.util.Optional;
import java.util.UUID;

public interface ApexDao {

  /**
   * Inserts data into currently loaded data storage for the player if not found..
   */
  void insert(ApexPlayer apexPlayer);

  /**
   * Saves data to currently loaded data storage on other thread
   */
  void save(ApexPlayer apexPlayer);

  /**
   * Saves data to currently loaded data storage on main thread.
   */
  void saveSync(ApexPlayer apexPlayer);

  /**
   * Deletes the player data from currently loaded data storage.
   */
  void delete(ApexPlayer apexPlayer);


  /**
   * Deletes the player data from currently loaded data storage.
   */
  void delete(UUID uuid);

  /**
   * Searches for data in currently loaded data storage
   *
   * @return Optional instance of {@link ApexPlayer} if exists in data storage
   */
  Optional<ApexPlayer> findByUniqueId(UUID uniqueId) throws Exception;

}
