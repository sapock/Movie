package movie.domain;

import movie.domain.*;
import movie.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class RatingDeleted extends AbstractEvent {

    private Long id;
    private Long movieId;
    private Long customerId;
    private Long reserveId;
    private Long score;
    private String review;

    public RatingDeleted(RatingInfo aggregate){
        super(aggregate);
    }
    public RatingDeleted(){
        super();
    }
}
