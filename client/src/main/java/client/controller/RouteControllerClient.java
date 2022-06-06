package client.controller;

import core.domain.Route;
import core.domain.validators.RailwayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import core.dto.RouteDTO;
import core.dto.RoutesDTO;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@Service
public class RouteControllerClient {
    public static final Logger logger = LoggerFactory.getLogger(RouteControllerClient.class);
    private final String url = "http://localhost:8080/api/routes";
    private final ExecutorService executorService;
    private final RestTemplate restTemplate;

    public RouteControllerClient(ExecutorService executorService, RestTemplate restTemplate) {
        this.executorService = executorService;
        this.restTemplate = restTemplate;
    }

    public CompletableFuture<Iterable<Route>> findAll() {
        logger.trace("FindAll route started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                RoutesDTO routesDTO = restTemplate.getForObject(url, RoutesDTO.class);
                if (routesDTO == null)
                    throw new RailwayException("Can't get routes from server!");
                return routesDTO.getRoutes().stream().map(routeDTO -> new Route(routeDTO.getId(), routeDTO.getSource(), routeDTO.getDestination())).collect(Collectors.toSet());
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> save(String source, String destination) {
        logger.trace("Save route started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                RouteDTO routeDTO = new RouteDTO(source, destination);
                restTemplate.postForObject(url, routeDTO, RouteDTO.class);
                return "Route added!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> update(Long routeID, String source, String destination) {
        logger.trace("Update route started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                RouteDTO routeDTO = new RouteDTO(source, destination);
                routeDTO.setId(routeID);
                restTemplate.put(url + "/{id}", routeDTO, routeDTO.getId());
                return "Route updated!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }

    public CompletableFuture<String> delete(Long id) {
        logger.trace("Delete route started (future).");
        return CompletableFuture.supplyAsync(() -> {
            try {
                restTemplate.delete(url + "/{id}", id);
                return "Route deleted!";
            } catch (ResourceAccessException rae) {
                throw new RailwayException("Can't access server!");
            }
        }, executorService);
    }
}
