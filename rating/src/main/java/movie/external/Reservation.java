package movie.external;

import lombok.Data;
import java.util.Date;
@Data
public class Reservation {

    private Long id;
    private Long movieId;
    private Long screenId;
    private Long customerId;
    private Integer seatingQty;
    private Object customerEmail;
    private String theaterName;
    private Date screenDate;
}


