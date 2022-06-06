package core.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
@ToString(callSuper = true)
public class DriverTrainSaveDTO {
    Long driverID;
    Long trainID;
    String howManyDays;
}
