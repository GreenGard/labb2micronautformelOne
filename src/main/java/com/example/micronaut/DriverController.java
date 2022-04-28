package com.example.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Status;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.reactivestreams.Publisher;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

import static io.micronaut.http.HttpStatus.CREATED;

@Controller("/drivers")
@ExecuteOn(TaskExecutors.IO)
public class DriverController  {

    private final DriverRepository driverService;

    public DriverController(DriverRepository driverService) {
        this.driverService = driverService;
    }

    @Get
    Publisher<Driver> list() {
        return driverService.list();
    }

    @Post
    @Status(CREATED)
    void save(@NonNull @NotNull @Valid Driver driver) {
        driverService.save(driver);
    }
}
