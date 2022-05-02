package com.example.micronaut;


import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.async.publisher.Publishers;
import jakarta.inject.Singleton;
import org.jetbrains.annotations.NotNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import java.util.List;

@Requires(property = "spec.name", value = "DriverControllerValidationTest")
@Singleton
@Replaces(DriverRepository.class)
public class MockDriverRepository implements DriverRepository {

    @Override
    public Publisher<Driver> list() {
        return null;
    }

    @Override
    public Mono<Boolean> save(@NotNull Driver driver) {
        return null;
    }
}