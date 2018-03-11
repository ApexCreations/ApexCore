package io.apexcreations.core;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import io.apexcreations.core.cache.ApexMapCache;

public class DependencyModule extends AbstractModule {

    private final ApexCore apexCore;

    DependencyModule(ApexCore apexCore) {
        this.apexCore = apexCore;
    }

    @Override
    protected void configure() {
        this.bind(ApexCore.class).to(apexCore.getClass());

        this.bind(ApexMapCache.class).annotatedWith(Names.named("PlayerCache"))
                .toInstance(this.apexCore.getPlayerCache());
    }
}
