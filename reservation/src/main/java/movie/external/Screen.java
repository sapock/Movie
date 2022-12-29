package movie.external;

import lombok.Data;
import java.util.Date;
@Data
public class Screen {

    private Long id;
    private Long movieId;
    private String theaterName;
    private Date screenDate;
    private Integer seatingCapacity;
    private Integer seatingRemain;
    private Object seatingPrice;
}


