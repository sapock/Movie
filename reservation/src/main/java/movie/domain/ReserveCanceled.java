package movie.domain;

import movie.domain.*;
import movie.infra.AbstractEvent;
import java.util.*;
import lombok.*;


@Data
@ToString
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private Long movieId;
    private Long screenId;
    private Long customerId;
    private Integer seatingQty;
    private String customerEmail;
    private String theaterName;
    private Date screenDate;

    public ReserveCanceled(Reservation aggregate){
        super(aggregate);
    }
    public ReserveCanceled(){
        super();
    }
}
