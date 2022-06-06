package web.controller;

import core.service.JoinServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import core.dto.TrainRouteFilterDTO;
import core.dto.TrainRouteFilterResponseDTO;

import java.util.List;

@RestController
public class JoinController {
    public static final Logger logger = LoggerFactory.getLogger(JoinController.class);
    private final JoinServiceInterface joinService;

    public JoinController(JoinServiceInterface joinService) {
        this.joinService = joinService;
    }

    @RequestMapping("/filterTrainsByRouteSource/{source}")
    TrainRouteFilterResponseDTO filterTrainsByRouteSource(@PathVariable String source){
        logger.trace("FilterTrainsByRouteSource started; source: " + source);
        List<TrainRouteFilterDTO> filteredTrains = this.joinService.filterTrainsByRouteSource(source);
        TrainRouteFilterResponseDTO response = new TrainRouteFilterResponseDTO(filteredTrains);
        logger.trace("FilterTrainsByRouteSource finished.");
        return response;
    }
}
