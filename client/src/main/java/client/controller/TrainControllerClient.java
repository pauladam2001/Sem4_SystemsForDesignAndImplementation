package client.controller;

import core.domain.Train;
import core.domain.validators.RailwayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import core.dto.TrainDTO;
import core.dto.TrainSaveDTO;
import core.dto.TrainUpdateDTO;
import core.dto.TrainsDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class TrainControllerClient {
    public static final Logger logger = LoggerFactory.getLogger(TrainControllerClient.class);
    private final String url = "http://localhost:8080/api/trains";
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;

    public TrainControllerClient(ExecutorService executorService, RestTemplate restTemplate) {
        this.executorService = executorService;
        this.restTemplate = restTemplate;
    }

    public CompletableFuture<Iterable<Train>> findAll() {
        logger.trace("FindAll train started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                TrainsDTO trainsDTO = restTemplate.getForObject(url, TrainsDTO.class);
                if (trainsDTO == null)
                    throw new RailwayException("Can't get trains from server!");
                return trainsDTO.getTrains().stream().map(trainDTO -> new Train(trainDTO.getId(), trainDTO.getRoute(), trainDTO.getDepartureTime(), trainDTO.getArrivalTime())).collect(Collectors.toSet());
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> save(Long routeID, String departure, String arrival) {
        logger.trace("Save train started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                TrainSaveDTO trainDTO = new TrainSaveDTO(departure, arrival, routeID);
                restTemplate.postForObject(url, trainDTO, TrainDTO.class);
                return "Train added!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> update(Long trainID, String departure, String arrival) {
        logger.trace("Update train started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                TrainUpdateDTO trainDTO = new TrainUpdateDTO(departure, arrival);
                trainDTO.setId(trainID);
                restTemplate.put(url + "/{id}", trainDTO, trainDTO.getId());
                return "Train updated!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> delete(Long id) {
        logger.trace("Delete train started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                restTemplate.delete(url + "/{id}", id);
                return "Train deleted!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }
}
