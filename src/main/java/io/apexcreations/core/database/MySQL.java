package io.apexcreations.core.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQL {

  private final String host;
  private final String user;
  private final String password;
  private final String database;
  private final int port;

  private Connection conn;

  MySQL(String host, int port, String user, String password, String database)
      throws Exception {
    this.host = host;
    this.port = port;
    this.user = user;
    this.password = password;
    this.database = database;
    this.openConnection();
  }

  public void update(String query) {
    checkConnection();
    try (PreparedStatement statement = this.conn.prepareStatement(query)) {
      update(statement);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void update(PreparedStatement statement) {
    checkConnection();
    try {
      statement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        statement.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void executeBatch(PreparedStatement preparedStatement) {
    checkConnection();
    try {
      preparedStatement.executeBatch();
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public ResultSet query(String query) {
    checkConnection();
    try {
      return query(this.conn.prepareStatement(query));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public ResultSet query(PreparedStatement statement) {
    checkConnection();
    try {
      return statement.executeQuery();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public Connection getConnection() {
    return this.conn;
  }

  private void checkConnection() {
    try {
      if (this.conn == null || !this.conn.isValid(10) || this.conn.isClosed()) {
        openConnection();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Connection openConnection() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    return this.conn = DriverManager
        .getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database,
            this.user, this.password);
  }

  public void closeConnection() {
    try {
      this.conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      this.conn = null;
    }
  }
}