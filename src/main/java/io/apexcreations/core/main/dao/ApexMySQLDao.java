package io.apexcreations.core.main.dao;

import io.apexcreations.core.ApexCore;
import io.apexcreations.core.api.dao.ApexDao;
import io.apexcreations.core.api.database.DatabaseAdapter;
import io.apexcreations.core.api.exceptions.MaxMoneyException;
import io.apexcreations.core.api.players.ApexPlayer;
import io.apexcreations.core.main.players.ApexPlayerImpl;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


public class ApexMySQLDao implements ApexDao {

  private final ApexCore apexCore;
  private final DatabaseAdapter databaseAdapter;
  private final String tableName;

  public ApexMySQLDao(ApexCore apexCore, DatabaseAdapter databaseAdapter) {
    this.apexCore = apexCore;
    this.databaseAdapter = databaseAdapter;
    this.tableName = "ApexCore-USERS";
  }

  @Override
  public void insert(ApexPlayer apexPlayer) {
    PreparedStatement preparedStatement = this.databaseAdapter.
        prepare(String.format("INSERT", this.tableName));
    //this.databaseAdapter.update();
  }

  @Override
  public void save(ApexPlayer apexPlayer) {

  }

  @Override
  public void saveSync(ApexPlayer apexPlayer) {

  }

  @Override
  public void delete(ApexPlayer apexPlayer) {
    this.delete(apexPlayer.getUniqueId());
  }

  @Override
  public void delete(UUID uniqueId) {
    PreparedStatement ps = this.databaseAdapter
        .prepare(String.format("DELETE FROM `%s` WHERE `uniqueId` = ?", this.tableName));
    try {
      ps.setString(1, uniqueId.toString());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    this.databaseAdapter.update(ps);
  }

  @Override // Needs a cleanup
  public Optional<ApexPlayer> findByUniqueId(UUID uniqueId) throws SQLException {
    PreparedStatement ps = this.databaseAdapter
        .prepare(String.format("SELECT * FROM `%s` WHERE uniqueId = ?", this.tableName));
    ps.setString(1, uniqueId.toString());
    AtomicReference<Optional<ApexPlayer>> optionalPlayer = new AtomicReference<>(Optional.empty());
    this.databaseAdapter.query(ps, resultSet -> {
      try {
        if (!resultSet.next()) {
          return;
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      ApexPlayer apexPlayer = new ApexPlayerImpl(uniqueId);
      try {
        apexPlayer.setStaffChat(resultSet.getBoolean("staffChatEnabled"));
        apexPlayer.getAccount().setBalance(resultSet.getBigDecimal("economyBalance"));
      } catch (MaxMoneyException | SQLException e) {
        e.printStackTrace();
      }
      optionalPlayer.set(Optional.of(apexPlayer));
    });
    return optionalPlayer.get();
  }
}