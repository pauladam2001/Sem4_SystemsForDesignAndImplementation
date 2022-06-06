package core.repository;

import core.domain.Train;
import core.dto.TrainRouteFilterDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JoinRepositoryInterface extends JpaRepository<Train, Long> {
    @Query("SELECT new core.dto.TrainRouteFilterDTO(t.id, t.arrivalTime, t.departureTime, r.id, r.source) FROM Train t INNER JOIN Route r ON t.route.id = r.id " +
            "WHERE r.source = :source")
    List<TrainRouteFilterDTO> filterTrainsByRouteSource(@Param("source") String source);
}
