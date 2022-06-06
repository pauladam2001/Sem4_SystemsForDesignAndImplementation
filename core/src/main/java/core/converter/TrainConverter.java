package core.converter;

import core.domain.Train;
import org.springframework.stereotype.Component;
import core.dto.TrainDTO;

@Component
public class TrainConverter extends BaseConverter<Long, Train, TrainDTO> {
    @Override
    public Train convertDtoToModel(TrainDTO dto) {
        var model = new Train();
        model.setId(dto.getId());
        model.setDepartureTime(dto.getDepartureTime());
        model.setArrivalTime(dto.getArrivalTime());
        model.setRoute(dto.getRoute());
        return model;
    }

    @Override
    public TrainDTO convertModelToDto(Train train) {
        TrainDTO dto = new TrainDTO();
        dto.setId(train.getId());
        dto.setArrivalTime(train.getArrivalTime());
        dto.setDepartureTime(train.getDepartureTime());
        dto.setRoute(train.getRoute());
        return dto;
    }
}
