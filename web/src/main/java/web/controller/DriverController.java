package web.controller;

import core.domain.Driver;
import core.service.DriverServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import core.converter.DriverConverter;
import core.dto.DriverDTO;
import core.dto.DriversDTO;

import java.util.List;

@RestController
public class DriverController {
    public static final Logger logger = LoggerFactory.getLogger(DriverController.class);
    private final DriverServiceInterface driverService;
    private final DriverConverter driverConverter;

    public DriverController(DriverServiceInterface driverService, DriverConverter driverConverter) {
        this.driverService = driverService;
        this.driverConverter = driverConverter;
    }

    @RequestMapping(value = "/drivers")
    DriversDTO findAll() {
        logger.trace("FindAll driver started.");
        List<Driver> drivers = driverService.findAll();
        DriversDTO driversDTO = new DriversDTO(driverConverter.convertModelsToDtos(drivers));
        logger.trace("Drivers: " + drivers);
        logger.trace("FindAll driver finished.");
        return driversDTO;
    }

    @RequestMapping(value = "/drivers", method = RequestMethod.POST)
    void save(@RequestBody DriverDTO driverDTO) {
        logger.trace("Save driver started; driverDTO: " + driverDTO);
        var driver = driverConverter.convertDtoToModel(driverDTO);
        driverService.save(driver.getName(), driver.getCNP());
        logger.trace("Save driver finished.");
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.PUT)
    void update(@PathVariable Long id, @RequestBody DriverDTO driverDTO) {
        logger.trace("Update driver started; driverDTO: " + driverDTO);
        var driver = driverConverter.convertDtoToModel(driverDTO);
        driverService.update(id, driver.getName(), driver.getCNP());
        logger.trace("Update driver finished.");
    }

    @RequestMapping(value = "/drivers/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id) {
        logger.trace("Delete driver started.");
        driverService.delete(id);
        logger.trace("Delete driver finished.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
