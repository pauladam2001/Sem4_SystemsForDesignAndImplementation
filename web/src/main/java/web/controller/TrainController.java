package web.controller;

import core.domain.Train;
import core.service.TrainServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import core.converter.TrainConverter;
import core.dto.TrainSaveDTO;
import core.dto.TrainUpdateDTO;
import core.dto.TrainsDTO;

import java.util.List;

@RestController
public class TrainController {
    public static final Logger logger = LoggerFactory.getLogger(TrainController.class);
    private final TrainServiceInterface trainService;
    private final TrainConverter trainConverter;

    public TrainController(TrainServiceInterface trainService, TrainConverter trainConverter) {
        this.trainService = trainService;
        this.trainConverter = trainConverter;
    }

    @RequestMapping(value = "/trains")
    TrainsDTO findAll() {
        logger.trace("FindAll train started.");
        List<Train> trains = trainService.findAll();
        TrainsDTO trainsDTO = new TrainsDTO(trainConverter.convertModelsToDtos(trains));
        logger.trace("Trains: " + trains);
        logger.trace("FindAll train finished.");
        return trainsDTO;
    }

    @RequestMapping(value = "/trains", method = RequestMethod.POST)
    void save(@RequestBody TrainSaveDTO trainSaveDTO) {
        logger.trace("Save train started; trainSaveDTO: " + trainSaveDTO);
        trainService.save(trainSaveDTO.getRouteID(), trainSaveDTO.getDepartureTime(), trainSaveDTO.getArrivalTime());
        logger.trace("Save train finished.");
    }

    @RequestMapping(value = "/trains/{id}", method = RequestMethod.PUT)
    void update(@PathVariable Long id, @RequestBody TrainUpdateDTO trainUpdateDTO) {
        logger.trace("Update train started; trainUpdateDTO: " + trainUpdateDTO);
        trainService.update(id, trainUpdateDTO.getDepartureTime(), trainUpdateDTO.getArrivalTime());
        logger.trace("Update train finished.");
    }

    @RequestMapping(value = "/trains/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id) {
        logger.trace("Delete train started.");
        trainService.delete(id);
        logger.trace("Delete train finished.");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/trains/filterTrainsBySource/{source}")
    TrainsDTO filterTrainsBySource(@PathVariable String source) {
        logger.trace("Filter trains by source started.");
        List<Train> filteredTrains = trainService.filterTrainsBySource(source);
        TrainsDTO trainsDTO = new TrainsDTO(trainConverter.convertModelsToDtos(filteredTrains));
        logger.trace("FilteredTrains: " + filteredTrains);
        logger.trace("Filter trains by source finished.");
        return trainsDTO;
    }

    @RequestMapping(value="/trains/sortTrainsByDeparture")
    TrainsDTO sortTrainsByDeparture () {
        logger.trace("Sort trains by departure started.");
        List<Train> sortedTrains = trainService.sortTrainsByDeparture();
        TrainsDTO trainsDTO = new TrainsDTO(trainConverter.convertModelsToDtos(sortedTrains));
        logger.trace("SortedTrains: " + sortedTrains);
        logger.trace("Sort trains by departure finished.");
        return trainsDTO;
    }
}
