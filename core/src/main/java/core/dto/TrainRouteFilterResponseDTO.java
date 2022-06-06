package core.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class TrainRouteFilterResponseDTO implements Serializable {
    List<TrainRouteFilterDTO> filteredTrainsDTO;
}