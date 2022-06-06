package web.controller;

import core.domain.DriverTrain;
import core.service.DriverTrainServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import core.converter.DriverTrainConverter;
import core.dto.DriverTrainSaveDTO;
import core.dto.DriverTrainUpdateDTO;
import core.dto.DriverTrainsDTO;

import java.util.List;

@RestController
public class DriverTrainController {
    public static final Logger logger = LoggerFactory.getLogger(DriverTrainController.class);
    private final DriverTrainServiceInterface driverTrainService;
    private final DriverTrainConverter driverTrainConverter;

    public DriverTrainController(DriverTrainServiceInterface driverTrainService, DriverTrainConverter driverTrainConverter) {
        this.driverTrainService = driverTrainService;
        this.driverTrainConverter = driverTrainConverter;
    }

    @RequestMapping(value = "/drivertrains")
    DriverTrainsDTO findAll() {
        logger.trace("FindAll driverTrain started.");
        List<DriverTrain> driverTrains = driverTrainService.findAll();
        DriverTrainsDTO driverTrainsDTO = new DriverTrainsDTO(driverTrainConverter.convertModelsToDtos(driverTrains));
        logger.trace("DriverTrains: " + driverTrains);
        logger.trace("FindAll driverTrain finished.");
        return driverTrainsDTO;
    }

    @RequestMapping(value = "/drivertrains", method = RequestMethod.POST)
    void save(@RequestBody DriverTrainSaveDTO driverTrainSaveDTO) {
        logger.trace("Save driverTrain started; driverTrainSaveDTO: " + driverTrainSaveDTO);
        driverTrainService.save(driverTrainSaveDTO.getDriverID(), driverTrainSaveDTO.getTrainID(), driverTrainSaveDTO.getHowManyDays());
        logger.trace("Save driverTrain finished.");
    }

    @RequestMapping(value = "/drivertrains/{id}", method = RequestMethod.PUT)
    void update(@PathVariable Long id, @RequestBody DriverTrainUpdateDTO driverTrainUpdateDTO) {
        logger.trace("Update driverTrain started; driverTrainUpdateDTO: " + driverTrainUpdateDTO);
        driverTrainService.update(id, driverTrainUpdateDTO.getHowManyDays());
        logger.trace("Update driverTrain finished.");
    }

    @RequestMapping(value = "/drivertrains/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id) {
        logger.trace("Delete driverTrain started.");
        driverTrainService.delete(id);
        logger.trace("Delete driverTrain finished.");
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
