package core.converter;

import core.domain.Driver;
import org.springframework.stereotype.Component;
import core.dto.DriverDTO;

@Component
public class DriverConverter extends BaseConverter<Long, Driver, DriverDTO> {
    @Override
    public Driver convertDtoToModel(DriverDTO dto) {
        var model = new Driver();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCNP(dto.getCNP());
        return model;
    }

    @Override
    public DriverDTO convertModelToDto(Driver driver) {
        DriverDTO dto = new DriverDTO(driver.getName(), driver.getCNP());
        dto.setId(driver.getId());
        return dto;
    }
}
