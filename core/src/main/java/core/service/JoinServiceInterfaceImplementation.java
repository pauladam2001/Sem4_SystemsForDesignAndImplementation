package core.service;

import core.dto.TrainRouteFilterDTO;
import core.repository.JoinRepositoryInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JoinServiceInterfaceImplementation implements JoinServiceInterface {
    private final JoinRepositoryInterface joinRepository;

    public JoinServiceInterfaceImplementation(JoinRepositoryInterface joinRepository){
        this.joinRepository = joinRepository;
    }

    @Override
    public List<TrainRouteFilterDTO> filterTrainsByRouteSource(String source) {
        return this.joinRepository.filterTrainsByRouteSource(source);
    }
}
