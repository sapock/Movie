package movie.domain;

import movie.domain.*;
import movie.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class MovieReserved extends AbstractEvent {

    private Long id;
    private Long screenId;
    private Long customerId;
    private Integer seatingQty;
    private Email customerEmail;
    private String theaterName;
    private Date screenDate;

    public MovieReserved(Reservation aggregate){
        super(aggregate);
    }
    public MovieReserved(){
        super();
    }
}
