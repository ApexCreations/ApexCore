package me.savvy.api.dao;

import java.util.Optional;
import java.util.UUID;
import me.savvy.api.players.ApexPlayer;

public interface ApexDao {

  /**
   * Inserts data into currently loaded data storage for the player if not found..
   * @param apexPlayer
   */
  void insert(ApexPlayer apexPlayer);

  /**
   * Saves data to currently loaded data storage on other thread
   * @param apexPlayer
   */
  void save(ApexPlayer apexPlayer);

  /**
   * Saves data to currently loaded data storage on main thread.
   * @param apexPlayer
   */
  void saveSync(ApexPlayer apexPlayer);

  /**
   * Deletes the player data from currently loaded data storage.
   * @param apexPlayer
   */
  void delete(ApexPlayer apexPlayer);


  /**
   * Deletes the player data from currently loaded data storage.
   * @param uuid
   */
  void delete(UUID uuid);

  /**
   * Searches for data in currently loaded data storage
   * @param uniqueId
   * @return Optional instance of {@link ApexPlayer} if exists in data storage
   */
  Optional<ApexPlayer> findByUniqueId(UUID uniqueId) throws Exception;

}
