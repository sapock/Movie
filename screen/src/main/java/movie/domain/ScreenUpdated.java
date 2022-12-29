package movie.domain;

import movie.domain.*;
import movie.infra.AbstractEvent;
import java.util.*;
import lombok.*;


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

    public ScreenUpdated(Screen aggregate){
        super(aggregate);
    }
    public ScreenUpdated(){
        super();
    }
}
