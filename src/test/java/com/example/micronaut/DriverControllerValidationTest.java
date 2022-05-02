package com.example.micronaut;

import io.micronaut.context.annotation.Property;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static io.micronaut.http.HttpStatus.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Property(name = "spec.name", value = "DriverControllerValidationTest")
@MicronautTest
public class DriverControllerValidationTest {
    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    public void testDriverIsValidated() {
        HttpClientResponseException e = assertThrows(HttpClientResponseException.class, () ->
                httpClient.toBlocking().exchange(HttpRequest.POST("/drivers", new Driver("Alonso"))));
        assertEquals(INTERNAL_SERVER_ERROR, e.getStatus());
    }
}
