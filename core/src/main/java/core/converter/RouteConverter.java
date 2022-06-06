package core.converter;

import core.domain.Route;
import org.springframework.stereotype.Component;
import core.dto.RouteDTO;

@Component
public class RouteConverter extends BaseConverter<Long, Route, RouteDTO> {
    @Override
    public Route convertDtoToModel(RouteDTO dto) {
        var model = new Route();
        model.setId(dto.getId());
        model.setSource(dto.getSource());
        model.setDestination(dto.getDestination());
        return model;
    }

    @Override
    public RouteDTO convertModelToDto(Route route) {
        RouteDTO dto = new RouteDTO(route.getSource(), route.getDestination());
        dto.setId(route.getId());
        return dto;
    }
}
