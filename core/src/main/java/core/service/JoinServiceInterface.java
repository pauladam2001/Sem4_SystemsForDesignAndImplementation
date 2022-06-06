package core.service;

import core.dto.TrainRouteFilterDTO;

import java.util.List;

public interface JoinServiceInterface {
    List<TrainRouteFilterDTO> filterTrainsByRouteSource(String source);
}
