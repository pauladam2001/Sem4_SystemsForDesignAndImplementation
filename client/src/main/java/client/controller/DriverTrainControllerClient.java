package client.controller;

import core.domain.DriverTrain;
import core.domain.validators.RailwayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import core.dto.DriverTrainDTO;
import core.dto.DriverTrainSaveDTO;
import core.dto.DriverTrainUpdateDTO;
import core.dto.DriverTrainsDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class DriverTrainControllerClient {
    public static final Logger logger = LoggerFactory.getLogger(DriverTrainControllerClient.class);
    private final String url = "http://localhost:8080/api/drivertrains";
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;

    public DriverTrainControllerClient(ExecutorService executorService, RestTemplate restTemplate) {
        this.executorService = executorService;
        this.restTemplate = restTemplate;
    }

    public CompletableFuture<Iterable<DriverTrain>> findAll() {
        logger.trace("FindAll driverTrain started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                DriverTrainsDTO driverTrainsDTO = restTemplate.getForObject(url, DriverTrainsDTO.class);
                if (driverTrainsDTO == null)
                    throw new RailwayException("Can't get driverTrains from server!");
                return driverTrainsDTO.getDriverTrains().stream().map(driverTrainDTO -> new DriverTrain(driverTrainDTO.getId(), driverTrainDTO.getDriver(), driverTrainDTO.getTrain(), driverTrainDTO.getHowManyDays())).collect(Collectors.toSet());
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> save(Long driverID, Long trainID, String howManyDays) {
        logger.trace("Save driverTrain started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                DriverTrainSaveDTO driverTrainDTO = new DriverTrainSaveDTO(driverID, trainID, howManyDays);
                restTemplate.postForObject(url, driverTrainDTO, DriverTrainDTO.class);
                return "DriverTrain added!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> update(Long driverTrainID, String howManyDays) {
        logger.trace("Update driverTrain started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                DriverTrainUpdateDTO driverTrainDTO = new DriverTrainUpdateDTO(howManyDays);
                driverTrainDTO.setId(driverTrainID);
                restTemplate.put(url + "/{id}", driverTrainDTO, driverTrainDTO.getId());
                return "DriverTrain updated!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> delete(Long id) {
        logger.trace("Delete driverTrain started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                restTemplate.delete(url + "/{id}", id);
                return "DriverTrain deleted!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }
}
