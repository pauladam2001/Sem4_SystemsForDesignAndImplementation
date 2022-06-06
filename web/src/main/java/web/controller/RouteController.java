package web.controller;

import core.domain.Route;
import core.service.RouteServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import core.converter.RouteConverter;
import core.dto.RouteDTO;
import core.dto.RoutesDTO;

import java.util.List;

@RestController
public class RouteController {
    public static final Logger logger = LoggerFactory.getLogger(RouteController.class);
    private final RouteServiceInterface routeService;
    private final RouteConverter routeConverter;

    public RouteController(RouteServiceInterface routeService, RouteConverter routeConverter) {
        this.routeService = routeService;
        this.routeConverter = routeConverter;
    }

    @RequestMapping(value = "/routes")
    RoutesDTO findAll() {
        logger.trace("FindAll route started.");
        List<Route> routes = routeService.findAll();
        RoutesDTO routesDTO = new RoutesDTO(routeConverter.convertModelsToDtos(routes));
        logger.trace("Routes: " + routes);
        logger.trace("FindAll route finished.");
        return routesDTO;
    }

    @RequestMapping(value = "/routes", method = RequestMethod.POST)
    void save(@RequestBody RouteDTO routeDTO) {
        logger.trace("Save route started; routeDTO: " + routeDTO);
        var route = routeConverter.convertDtoToModel(routeDTO);
        routeService.save(route.getSource(), route.getDestination());
        logger.trace("Save route finished.");
    }

    @RequestMapping(value = "/routes/{id}", method = RequestMethod.PUT)
    void update(@PathVariable Long id, @RequestBody RouteDTO routeDTO) {
        logger.trace("Update route started; routeDTO: " + routeDTO);
        var route = routeConverter.convertDtoToModel(routeDTO);
        routeService.update(id, route.getSource(), route.getDestination());
        logger.trace("Update route finished.");
    }

    @RequestMapping(value = "/routes/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id) {
        logger.trace("Delete route started.");
        routeService.delete(id);
        logger.trace("Delete route finished.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
