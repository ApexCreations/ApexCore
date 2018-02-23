package me.savvy.main.dao;

import java.util.Optional;
import java.util.UUID;
import me.savvy.api.dao.ApexDao;
import me.savvy.api.database.DatabaseAdapter;
import me.savvy.api.players.ApexPlayer;

public class ApexMySQLDao implements ApexDao {

  private DatabaseAdapter databaseAdapter;

  public ApexMySQLDao() {
    this.databaseAdapter = null;
  }
  @Override
  public void insert(ApexPlayer apexPlayer) {

  }

  @Override
  public void save(ApexPlayer apexPlayer) {

  }

  @Override
  public void saveSync(ApexPlayer apexPlayer) {

  }

  @Override
  public void delete(ApexPlayer apexPlayer) {

  }

  @Override
  public void delete(UUID uuid) {

  }

  @Override
  public Optional<ApexPlayer> findByUniqueId(UUID uniqueId) {
    return Optional.empty();
  }
}
