package movie.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Date;
import lombok.Data;


@Entity
@Table(name="MovieRatingInfo_table")
@Data
public class MovieRatingInfo {

        @Id
        //@GeneratedValue(strategy=GenerationType.AUTO)
        private Long id;
        private Long customerId;
        private Long score;
        private String review;
        private Long movieId;


}
