package movie.domain;

import movie.domain.*;
import movie.infra.AbstractEvent;
import lombok.*;
import java.util.*;
@Data
@ToString
public class MovieReserved extends AbstractEvent {

    private Long id;
    private Long movieId;
    private Long screenId;
    private Long customerId;
    private Integer seatingQty;
    private String customerEmail;
    private String theaterName;
    private Date screenDate;
}


