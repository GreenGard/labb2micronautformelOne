package com.example.micronaut;

import io.micronaut.core.annotation.NonNull;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


public interface DriverRepository {

    @NonNull
    Publisher<Driver> list();

    Mono<Boolean> save(@NonNull @NotNull @Valid Driver driver);
    }

