package com.example.micronaut;

import io.micronaut.http.HttpStatus;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;
import java.util.Map;

import static com.mongodb.assertions.Assertions.*;
import static io.micronaut.http.HttpStatus.CREATED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@MicronautTest
@TestInstance(PER_CLASS)
public class DriverControllerTest implements TestPropertyProvider {

    @Inject
    DriverClient driverClient;

    @Test void driversEndpointInteractsWithMongo(){
        List<Driver> drivers=driverClient.findAll();
        assertTrue(drivers.isEmpty());

    HttpStatus status = driverClient.save(new Driver("Alonso"));
    assertEquals(CREATED, status);

    drivers = driverClient.findAll();
    assertFalse(drivers.isEmpty());
    assertEquals("Alonso", drivers.get(0).getName());
    assertNull(drivers.get(0).getDescription());

    status = driverClient.save(new Driver("hejsab", "what"));
    assertEquals(CREATED, status);

    drivers = driverClient.findAll();
    assertTrue(drivers.stream().anyMatch(f -> "shat".equals(f.getDescription())));
}

    @AfterAll
    static void cleanup() {
        MongoDbUtils.closeMongoDb();
    }

    @Override
    public Map<String, String> get() {
        return TestPropertyProvider.super.get();
    }

    @Override
    public Map<String, String> getProperties() {
        return null;
    }
}
