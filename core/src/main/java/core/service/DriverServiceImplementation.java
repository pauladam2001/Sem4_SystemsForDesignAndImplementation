package core.service;

import core.domain.Driver;
import core.domain.validators.RailwayException;
import core.repository.DriverRepositoryInterface;
import core.repository.DriverTrainRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Math.max;

@Service
public class DriverServiceImplementation implements DriverServiceInterface {
    private final DriverRepositoryInterface driverRepository;
    private final DriverTrainRepositoryInterface driverTrainRepository;
    public static final Logger logger = LoggerFactory.getLogger(DriverServiceImplementation.class);

    public DriverServiceImplementation(DriverRepositoryInterface driverRepository, DriverTrainRepositoryInterface driverTrainRepository) {
        this.driverRepository = driverRepository;
        this.driverTrainRepository = driverTrainRepository;
    }

    @Override
    public List<Driver> findAll() {
        logger.trace("FindAll driver started.");
        List<Driver> drivers = driverRepository.findAll();
        logger.trace("Drivers: " + drivers);
        logger.trace("FindAll driver finished.");
        return drivers;
    }

    @Override
    public void save(String name, String CNP) {
        logger.trace("Save driver started; name: " + name + ", CNP: " + CNP);
        long driverID = 0;
        for (Driver driver : this.driverRepository.findAll()) {
            driverID = max(driverID, driver.getId() + 1);
        }
        Driver driverToBeAdded = new Driver(driverID, name, CNP);
        driverRepository.save(driverToBeAdded);
        logger.trace("Save driver finished.");
    }

    @Override
    @Transactional
    public void update(Long driverID, String name, String CNP) {
        logger.trace("Update driver started; id: " + driverID + ", name: " + name + ", CNP: " + CNP);
        driverRepository.findById(driverID)
                .ifPresentOrElse((driver) -> {
                    driver.setName(name);
                    driver.setCNP(CNP);
                },
                () -> {
                    throw new RailwayException("Driver is not in the database!");
                });
        logger.trace("Update driver finished.");
    }

    @Override
    public void delete(Long driverID) {
        logger.trace("Delete driver started; id: " + driverID);
        driverRepository.findById(driverID)
                .ifPresentOrElse((driver) -> driverRepository.deleteById(driver.getId()),
                        () -> {
                            throw new RailwayException("Driver is not in the database!");
                        });
        logger.trace("Delete driver finished.");
    }
}
