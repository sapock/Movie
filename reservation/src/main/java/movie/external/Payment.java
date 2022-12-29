package movie.external;

import lombok.Data;
import java.util.Date;
@Data
public class Payment {

    private Long id;
    private Long reserveId;
    private Object amount;
    private Date approveDate;
    private String payStatus;
    private Long customerId;
}


