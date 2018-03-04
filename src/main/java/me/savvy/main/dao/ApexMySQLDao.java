package me.savvy.main.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import me.savvy.ApexCore;
import me.savvy.api.dao.ApexDao;
import me.savvy.api.database.DatabaseAdapter;
import me.savvy.api.exceptions.MaxMoneyException;
import me.savvy.api.players.ApexPlayer;
import me.savvy.main.players.ApexPlayerImpl;

public class ApexMySQLDao implements ApexDao {

  private final ApexCore apexCore;
  private final DatabaseAdapter databaseAdapter;
  private final String tableName;

  public ApexMySQLDao(DatabaseAdapter databaseAdapter) {
    this.apexCore = ApexCore.getInstance();
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