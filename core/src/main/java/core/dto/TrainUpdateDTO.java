package core.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TrainUpdateDTO extends BaseDTO<Long> {
    String departureTime;
    String arrivalTime;
}
