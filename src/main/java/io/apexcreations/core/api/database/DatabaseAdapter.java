package io.apexcreations.core.api.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.function.Supplier;
import me.savvy.ApexCore;
import org.bukkit.Bukkit;

public class DatabaseAdapter {

  private ExecutorService executor;
  private ApexCore apexCore;
  private MySQL sql;

  public DatabaseAdapter(String host, int port, String user, String password,
      String database) {
    try {
      this.apexCore = ApexCore.getInstance();
      this.sql = new MySQL(host, port, user, password, database);
      this.executor = Executors.newCachedThreadPool();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void update(PreparedStatement statement) {
    this.executor.execute(() -> this.sql.update(statement));
  }

  public void update(String statement) {
    this.executor.execute(() -> this.sql.update(statement));
  }

  public void executeBatch(PreparedStatement preparedStatement) {
    this.executor.execute(() -> this.sql.executeBatch(preparedStatement));
  }

  public void query(PreparedStatement statement, Consumer<ResultSet> consumer) {
    this.executor.execute(() -> {
      ResultSet result = this.sql.query(statement);
      Bukkit.getScheduler().runTaskAsynchronously(this.apexCore, () -> {
        consumer.andThen(after -> {
          try {
            if (!statement.isClosed()) {
              statement.close();
            }
            if (!result.isClosed()) {
              result.close();
            }
          } catch (SQLException ex) {
            ex.printStackTrace();
          }
        });
        consumer.accept(result);
      });
    });
  }

  public void query(String statement, Consumer<ResultSet> consumer) {
    this.query(this.prepare(statement), consumer);
  }

  public PreparedStatement prepare(String query) {
    try {
      return this.sql.getConnection().prepareStatement(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public MySQL getMySQL() {
    return this.sql;
  }
  
  // TODO: Mongo
}
