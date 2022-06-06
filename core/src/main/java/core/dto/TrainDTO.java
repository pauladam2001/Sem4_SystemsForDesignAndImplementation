package core.dto;

import core.domain.Route;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrainDTO extends BaseDTO<Long> {
    String departureTime;
    String arrivalTime;
    Route route;
}
