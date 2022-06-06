package core.dto;

import lombok.*;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class TrainRouteFilterDTO implements Serializable {
    long ID;
    String departure;
    String arrival;
    long routeID;
    String source;
}