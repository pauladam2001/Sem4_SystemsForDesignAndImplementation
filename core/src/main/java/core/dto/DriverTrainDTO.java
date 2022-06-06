package core.dto;

import core.domain.Driver;
import core.domain.Train;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DriverTrainDTO extends BaseDTO<Long> {
    Driver driver;
    Train train;
    String howManyDays;
}
