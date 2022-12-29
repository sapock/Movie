package movie.domain;

import movie.domain.RatingRegistered;
import movie.domain.RatingDeleted;
import movie.RatingApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "RatingInfo_table")
@Data

public class RatingInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private Long movieId;

    private Long customerId;

    private Long reserveId;

    private Long score;

    private String review;

    @PostPersist
    public void onPostPersist() {

        RatingRegistered ratingRegistered = new RatingRegistered(this);
        ratingRegistered.publishAfterCommit();

        RatingDeleted ratingDeleted = new RatingDeleted(this);
        ratingDeleted.publishAfterCommit();

        // Get request from Reservation
        // movie.external.Reservation reservation =
        // Application.applicationContext.getBean(movie.external.ReservationService.class)
        // .getReservation(/** mapping value needed */);

    }

    public static RatingInfoRepository repository() {
        RatingInfoRepository ratingInfoRepository = RatingApplication.applicationContext
                .getBean(RatingInfoRepository.class);
        return ratingInfoRepository;
    }

}