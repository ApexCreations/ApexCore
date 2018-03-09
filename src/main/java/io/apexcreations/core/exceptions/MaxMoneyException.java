package io.apexcreations.core.exceptions;

public class MaxMoneyException extends Exception {

  public MaxMoneyException() {
    super("This transaction would exceed the balance limit for this account!");
  }
}
