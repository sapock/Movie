package movie.domain;

import movie.domain.*;
import movie.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class ScreenUpdated extends AbstractEvent {

    private Long id;
    private Long movieId;
    private String theaterName;
    private Date screenDate;
    private Integer seatingCapacity;
    private Integer seatingRemain;
    private Integer seatingPrice;
}


