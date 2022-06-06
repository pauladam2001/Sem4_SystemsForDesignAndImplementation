package core.service;

import core.domain.Driver;
import core.domain.DriverTrain;
import core.domain.Train;
import core.domain.validators.RailwayException;
import core.repository.DriverRepositoryInterface;
import core.repository.DriverTrainRepositoryInterface;
import core.repository.TrainRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.max;

@Service
public class DriverTrainServiceImplementation implements DriverTrainServiceInterface {
    private final DriverRepositoryInterface driverRepository;
    private final DriverTrainRepositoryInterface driverTrainRepository;
    private final TrainRepositoryInterface trainRepository;
    public static final Logger logger = LoggerFactory.getLogger(DriverTrainServiceImplementation.class);

    public DriverTrainServiceImplementation(DriverRepositoryInterface driverRepository, DriverTrainRepositoryInterface driverTrainRepository, TrainRepositoryInterface trainRepository) {
        this.driverRepository = driverRepository;
        this.driverTrainRepository = driverTrainRepository;
        this.trainRepository = trainRepository;
    }

    @Override
    public List<DriverTrain> findAll() {
        logger.trace("FindAll driverTrain started.");
        List<DriverTrain> driverTrains = driverTrainRepository.findAll();
        logger.trace("DriverTrains: " + driverTrains);
        logger.trace("FindAll driverTrain finished.");
        return driverTrains;
    }

    @Override
    public void save(Long driverID, Long trainID, String howManyDays) {
        logger.trace("Save driverTrain started; driverID: " + driverID + ", trainID: " + trainID + ", howManyDays: " + howManyDays);
        Optional<Driver> driver = driverRepository.findById(driverID);
        Optional<Train> train = trainRepository.findById(trainID);
        driver.ifPresentOrElse(
                (Driver d) -> {
                    train.ifPresentOrElse(
                            (Train t) -> {
                                long driverTrainID = 0;
                                for (DriverTrain driverTrain : this.driverTrainRepository.findAll()) {
                                    driverTrainID = max(driverTrainID, driverTrain.getId() + 1);
                                }
                                DriverTrain driverTrainToBeAdded = new DriverTrain(driverTrainID, d, t, howManyDays);
                                driverTrainRepository.save(driverTrainToBeAdded);
                            },
                            () -> {
                                throw new RailwayException("Train is not in the database!");
                            }
                    );
                },
                () -> {
                    throw new RailwayException("Driver is not in the database!");
                }
        );
        logger.trace("Save driverTrain finished.");
    }

    @Override
    @Transactional
    public void update(Long driverTrainID, String howManyDays) {
        logger.trace("Update driverTrain started; id: " + driverTrainID + ", howManyDays: " + howManyDays);
        driverTrainRepository.findById(driverTrainID)
                .ifPresentOrElse((driverTrain) -> driverTrain.setHowManyDays(howManyDays),
                        () -> {
                            throw new RailwayException("DriverTrain is not in the database!");
                        });
        logger.trace("Update driverTrain finished.");
    }

    @Override
    public void delete(Long driverTrainID) {
        logger.trace("Delete driverTrain started; id: " + driverTrainID);
        driverTrainRepository.findById(driverTrainID)
                .ifPresentOrElse((driverTrain) -> driverTrainRepository.deleteById(driverTrain.getId()),
                        () -> {
                            throw new RailwayException("DriverTrain is not in the database!");
                        });
        logger.trace("Delete driverTrain finished.");
    }
}
