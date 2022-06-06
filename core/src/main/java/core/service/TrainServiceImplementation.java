package core.service;

import core.domain.Route;
import core.domain.Train;
import core.domain.validators.RailwayException;
import core.repository.RouteRepositoryInterface;
import core.repository.TrainRepositoryInterface;
import core.repository.DriverTrainRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.max;

@Service
public class TrainServiceImplementation implements TrainServiceInterface {
    private final TrainRepositoryInterface trainRepository;
    private final RouteRepositoryInterface routeRepository;
    private final DriverTrainRepositoryInterface driverTrainRepository;
    public static final Logger logger = LoggerFactory.getLogger(TrainServiceImplementation.class);

    public TrainServiceImplementation(RouteRepositoryInterface routeRepository, TrainRepositoryInterface trainRepository, DriverTrainRepositoryInterface driverTrainRepository) {
        this.routeRepository = routeRepository;
        this.trainRepository = trainRepository;
        this.driverTrainRepository = driverTrainRepository;
    }

    @Override
    public List<Train> findAll() {
        logger.trace("FindAll train started.");
        List<Train> trains = trainRepository.findAll();
        logger.trace("Trains: " + trains);
        logger.trace("FindAll train finished.");
        return trains;
    }

    @Override
    public void save(Long routeID, String departure, String arrival) {
        logger.trace("Save train started; routeID: " + routeID + ", departure: " + departure + ", arrival: " + arrival);
        Optional<Route> route = routeRepository.findById(routeID);
        route.ifPresentOrElse(
                (Route r) -> {
                    long trainID = 0;
                    for (Train train : this.trainRepository.findAll()) {
                        trainID = max(trainID, train.getId() + 1);
                    }
                    Train trainToBeAdded = new Train(trainID, r, departure, arrival);
                    trainRepository.save(trainToBeAdded);
                },
                () -> {
                    throw new RailwayException("Train is not in the database!");
                }
        );
        logger.trace("Save train finished.");
    }

    @Override
    @Transactional
    public void update(Long trainID, String departure, String arrival) {
        logger.trace("Update train started; id: " + trainID + ", departure: " + departure + ", arrival: " + arrival);
        trainRepository.findById(trainID)
                .ifPresentOrElse((train) -> {
                            train.setArrivalTime(arrival);
                            train.setDepartureTime(departure);
                        },
                        () -> {
                            throw new RailwayException("Train is not in the database!");
                        });
        logger.trace("Update train finished.");
    }

    @Override
    public void delete(Long trainID) {
        logger.trace("Delete train started; id: " + trainID);
        trainRepository.findById(trainID)
                .ifPresentOrElse((train) -> trainRepository.deleteById(train.getId()),
                        () -> {
                            throw new RailwayException("Train is not in the database!");
                        });
        logger.trace("Delete train finished.");
    }

    @Override
    public List<Train> filterTrainsBySource(String source) {
        return trainRepository.filterTrainsBySource(source);
    }

    @Override
    public List<Train> sortTrainsByDeparture() {
        return trainRepository.sortTrainsByDeparture();
    }
}
