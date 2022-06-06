package core.converter;

import core.domain.DriverTrain;
import org.springframework.stereotype.Component;
import core.dto.DriverTrainDTO;

@Component
public class DriverTrainConverter extends BaseConverter<Long, DriverTrain, DriverTrainDTO> {
    @Override
    public DriverTrain convertDtoToModel(DriverTrainDTO dto) {
        var model = new DriverTrain();
        model.setId(dto.getId());
        model.setDriver(dto.getDriver());
        model.setTrain(dto.getTrain());
        model.setHowManyDays(dto.getHowManyDays());
        return model;
    }

    @Override
    public DriverTrainDTO convertModelToDto(DriverTrain driverTrain) {
        DriverTrainDTO dto = new DriverTrainDTO();
        dto.setId(driverTrain.getId());
        dto.setDriver(driverTrain.getDriver());
        dto.setTrain(driverTrain.getTrain());
        dto.setHowManyDays(driverTrain.getHowManyDays());
        return dto;
    }
}
