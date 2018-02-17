package me.savvy.main.account;

import java.util.UUID;

public class Account {

  private final UUID accountOwner;
  private double balance;

  public Account(UUID accountOwner) {
    this.accountOwner = accountOwner;
  }

  public double getBalance() {
    return this.balance;
  }

  public void addToBalance(double amount) {
    this.setBalance(this.getBalance() + amount);
  }

  public void removeFromBalance(double amount) {
    this.setBalance(this.getBalance() - amount);
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public UUID getAccountOwner() {
    return this.accountOwner;
  }
}
