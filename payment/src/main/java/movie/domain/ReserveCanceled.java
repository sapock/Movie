package movie.domain;

import movie.domain.*;
import movie.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private Long screenId;
    private Long customerId;
    private Integer seatingQty;
    private Object customerEmail;
    private String theaterName;
    private Date screenDate;
}


