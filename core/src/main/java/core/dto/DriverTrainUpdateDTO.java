package core.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DriverTrainUpdateDTO extends BaseDTO<Long> {
    String howManyDays;
}
