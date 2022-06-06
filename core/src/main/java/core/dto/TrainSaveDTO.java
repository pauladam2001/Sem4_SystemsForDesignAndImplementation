package core.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString(callSuper = true)
public class TrainSaveDTO {
    String departureTime;
    String arrivalTime;
    Long routeID;
}
