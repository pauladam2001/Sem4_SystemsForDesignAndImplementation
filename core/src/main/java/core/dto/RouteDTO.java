package core.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RouteDTO extends BaseDTO<Long> {
    String source;
    String destination;
}
