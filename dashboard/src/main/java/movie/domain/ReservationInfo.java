package movie.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;


@Entity
@Table(name="ReservationInfo_table")
@Data
public class ReservationInfo {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long reservId;
        private String movieSubject;
        private Long screenId;
        private Date screenDate;
        private Integer seatingQty;


}
