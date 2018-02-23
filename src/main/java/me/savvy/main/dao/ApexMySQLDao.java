package me.savvy.main.dao;

import java.util.Optional;
import java.util.UUID;
import me.savvy.ApexCore;
import me.savvy.api.dao.ApexDao;
import me.savvy.api.database.DatabaseAdapter;
import me.savvy.api.players.ApexPlayer;

public class ApexMySQLDao implements ApexDao {

  private ApexCore apexCore;
  private DatabaseAdapter databaseAdapter;

  public ApexMySQLDao() {
    this.apexCore = ApexCore.getInstance();
    this.databaseAdapter = this.apexCore.getApexAPI().getDatabaseAdapter();
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
