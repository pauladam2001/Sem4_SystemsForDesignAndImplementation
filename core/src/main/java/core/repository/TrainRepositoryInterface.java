package core.repository;

import core.domain.Train;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainRepositoryInterface extends RepositoryInterface<Train, Long> {
    @Query(value = "SELECT t FROM Train t WHERE t.route.source=:source")
    List<Train> filterTrainsBySource(@Param("source") String source);

    @Query(value = "SELECT t FROM Train t ORDER BY t.departureTime")
    List<Train> sortTrainsByDeparture();
}
