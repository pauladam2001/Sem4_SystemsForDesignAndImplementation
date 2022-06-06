package core.service;

import core.domain.DriverTrain;

import java.util.List;

public interface DriverTrainServiceInterface {
    List<DriverTrain> findAll();

    void save(Long driverID, Long trainID, String howManyDays);

    void update(Long driverTrainID, String howManyDays);

    void delete(Long driverTrainID);
}
