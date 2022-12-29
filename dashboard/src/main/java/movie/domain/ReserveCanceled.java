package movie.domain;

import movie.infra.AbstractEvent;
import lombok.Data;
import java.util.*;


@Data
public class ReserveCanceled extends AbstractEvent {

    private Long id;
    private Long screenId;
    private Long customerId;
    private Integer seatingQty;
    private Email customerEmail;
    private String theaterName;
    private Date screenDate;
}
