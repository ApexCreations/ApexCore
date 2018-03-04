package me.savvy.api.database.enums;

public enum SQLStatements {

  CREATE_USERS(
      "CREATE TABLE IF NOT EXISTS `%s` "
      + "(`uniqueId` VARCHAR(36) NOT NULL, "
      + "`economyBalance` DECIMAL(20) NOT NULL DEFAULT '0', "
      + "`staffChat` BOOLEAN NOT NULL DEFAULT FALSE, "
      + "`homes` TEXT NOT NULL);)");

  private String s;
  SQLStatements(String s) {
    this.s = s;
  }

  public String getQuery(Object... objs) {
    return String.format(s, objs);
  }
}
