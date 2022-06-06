package client.controller;

import core.domain.validators.RailwayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import core.dto.TrainRouteFilterDTO;
import core.dto.TrainRouteFilterResponseDTO;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class JoinControllerClient {
    public static final Logger logger = LoggerFactory.getLogger(JoinControllerClient.class);
    private final String url = "http://localhost:8080/api";
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;

    public JoinControllerClient(ExecutorService executorService, RestTemplate restTemplate){
        this.executorService = executorService;
        this.restTemplate = restTemplate;
    }

    public CompletableFuture<List<TrainRouteFilterDTO>> filterTrainsByRouteSource(String source) {
        logger.trace("FilterTrainsByRouteSource started (future); source: " + source);
        return CompletableFuture.supplyAsync(() -> {
            try {
                TrainRouteFilterResponseDTO filteredTrains = restTemplate.getForObject(url + "/filterTrainsByRouteSource/" + source, TrainRouteFilterResponseDTO.class);
                if (filteredTrains == null)
                    throw new RailwayException("There is no data for the given filter");
                return filteredTrains.getFilteredTrainsDTO();
            } catch (ResourceAccessException e) {
                throw new RailwayException("Inaccessible server");
            }
        }, executorService);
    }
}
