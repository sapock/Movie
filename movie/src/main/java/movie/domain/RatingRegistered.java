package movie.domain;

import movie.domain.*;
import movie.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class RatingRegistered extends AbstractEvent {

    private Long id;
    private Long movieId;
    private Long customerId;
    private Long reserveId;
    private Long score;
    private String review;
}


