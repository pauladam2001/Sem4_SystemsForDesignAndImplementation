package core.service;

import core.domain.Route;
import core.domain.validators.RailwayException;
import core.repository.RouteRepositoryInterface;
import core.repository.TrainRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Math.max;

@Service
public class RouteServiceImplementation implements RouteServiceInterface {
    private final RouteRepositoryInterface routeRepository;
    private final TrainRepositoryInterface trainRepository;
    public static final Logger logger = LoggerFactory.getLogger(RouteServiceImplementation.class);

    public RouteServiceImplementation(RouteRepositoryInterface routeRepository, TrainRepositoryInterface trainRepository) {
        this.routeRepository = routeRepository;
        this.trainRepository = trainRepository;
    }

    @Override
    public List<Route> findAll() {
        logger.trace("FindAll route started.");
        List<Route> routes = routeRepository.findAll();
        logger.trace("Routes: " + routes);
        logger.trace("FindAll route finished.");
        return routes;
    }

    @Override
    public void save(String source, String destination) {
        logger.trace("Save route started; source: " + source + ", destination: " + destination);
        long routeID = 0;
        for (Route route : this.routeRepository.findAll()) {
            routeID = max(routeID, route.getId() + 1);
        }
        Route routeToBeAdded = new Route(routeID, source, destination);
        routeRepository.save(routeToBeAdded);
        logger.trace("Save route finished.");
    }

    @Override
    @Transactional
    public void update(Long routeID, String source, String destination) {
        logger.trace("Update route started; id: " + routeID + ", source: " + source + ", destination: " + destination);
        routeRepository.findById(routeID)
                .ifPresentOrElse((route) -> {
                            route.setSource(source);
                            route.setDestination(destination);
                        },
                        () -> {
                            throw new RailwayException("Route is not in the database!");
                        });
        logger.trace("Update route finished.");
    }

    @Override
    public void delete(Long routeID) {
        logger.trace("Delete route started; id: " + routeID);
        routeRepository.findById(routeID)
                .ifPresentOrElse((route) -> routeRepository.deleteById(route.getId()),
                        () -> {
                            throw new RailwayException("Route is not in the database!");
                        });
        logger.trace("Delete route finished.");
    }
}
