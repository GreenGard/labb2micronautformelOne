package com.example.micronaut;

import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.micronaut.http.HttpStatus.CREATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@MicronautTest
@TestInstance(PER_CLASS)
public class DriverControllerTest implements TestPropertyProvider {

    @Inject
    DriverClient driverClient;

    @Test
    void fruitsEndpointInteractsWithMongo() {

        List<Driver> drivers = driverClient.findAll();
        assertTrue(drivers.isEmpty());

        HttpStatus status = driverClient.save(new Driver("Madelene"));
        assertEquals(CREATED, status);

        drivers = driverClient.findAll();
        assertFalse(drivers.isEmpty());
        assertEquals("Madelene", drivers.get(0).getName());
        assertNull(drivers.get(0).getDescription());

        status = driverClient.save(new Driver("Per", "Keeps the doctor away"));
        assertEquals(CREATED, status);

        drivers = driverClient.findAll();
        assertTrue(drivers.stream().anyMatch(f -> "Keeps the doctor away".equals(f.getDescription())));
    }

    @AfterAll
    static void cleanup() {
        MongoDbUtils.closeMongoDb();
    }

    @Override
    public Map<String, String> getProperties() {
        MongoDbUtils.startMongoDb();
        return Collections.singletonMap("mongodb.uri", MongoDbUtils.getMongoDbUri());
    }
}