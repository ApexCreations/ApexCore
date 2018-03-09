package io.apexcreations.core;

import com.google.inject.AbstractModule;

public class DependencyModule extends AbstractModule {

  private final ApexCore apexCore;

  DependencyModule(ApexCore apexCore) {
    this.apexCore = apexCore;
  }

  @Override
  protected void configure() {
    this.bind(ApexCore.class).toInstance(apexCore);
  }
}
