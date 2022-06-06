package core.service;

import core.domain.Train;

import java.util.List;

public interface TrainServiceInterface {
    List<Train> findAll();

    void save(Long routeID, String departure, String arrival);

    void update(Long trainID, String departure, String arrival);

    void delete(Long trainID);

    List<Train> filterTrainsBySource(String source);

    List<Train> sortTrainsByDeparture();
}
