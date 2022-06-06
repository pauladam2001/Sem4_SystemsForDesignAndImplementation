package client.controller;

import core.domain.Driver;
import core.domain.validators.RailwayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import core.dto.DriverDTO;
import core.dto.DriversDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class DriverControllerClient {
    public static final Logger logger = LoggerFactory.getLogger(DriverControllerClient.class);
    private final String url = "http://localhost:8080/api/drivers";
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;

    public DriverControllerClient(ExecutorService executorService, RestTemplate restTemplate) {
        this.executorService = executorService;
        this.restTemplate = restTemplate;
    }

    public CompletableFuture<Iterable<Driver>> findAll() {
        logger.trace("FindAll driver started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                DriversDTO driversDTO = restTemplate.getForObject(url, DriversDTO.class);
                if (driversDTO == null)
                    throw new RailwayException("Can't get drivers from server!");
                return driversDTO.getDrivers().stream().map(driverDTO -> new Driver(driverDTO.getId(), driverDTO.getName(), driverDTO.getCNP())).collect(Collectors.toSet());
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> save(String name, String CNP) {
        logger.trace("Save driver started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                DriverDTO driverDTO = new DriverDTO(name, CNP);
                restTemplate.postForObject(url, driverDTO, DriverDTO.class);
                return "Driver added!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> update(Long driverID, String name, String CNP) {
        logger.trace("Update driver started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                DriverDTO driverDTO = new DriverDTO(name, CNP);
                driverDTO.setId(driverID);
                restTemplate.put(url + "/{id}", driverDTO, driverDTO.getId());
                return "Driver updated!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> delete(Long id) {
        logger.trace("Delete driver started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                restTemplate.delete(url + "/{id}", id);
                return "Driver deleted!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }
}
