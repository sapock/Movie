package movie.domain;

import movie.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class RatingRegistered extends AbstractEvent {

    private Long id;
    private Long movieId;
    private Long customerId;
    private Long reserveId;
    private Long score;
    private String review;
}
