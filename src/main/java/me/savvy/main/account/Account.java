package me.savvy.main.account;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {

  private final UUID accountOwner;
  private BigDecimal balance;

  public Account(UUID accountOwner) {
    this.accountOwner = accountOwner;
  }

  public BigDecimal getBalance() {
    return this.balance;
  }

  public void addToBalance(double amount) {
    this.setBalance(this.balance.add(this.toBigDecimal(amount)));
  }

  public void removeFromBalance(double amount) {
    this.setBalance(this.balance.subtract(this.toBigDecimal(amount)));
  }

  public void setBalance(BigDecimal balance) {
    this.balance = balance;
  }

  public UUID getAccountOwner() {
    return this.accountOwner;
  }

  private BigDecimal toBigDecimal(double amount) {
    return BigDecimal.valueOf(amount);
  }
}
