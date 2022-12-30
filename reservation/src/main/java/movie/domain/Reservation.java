package movie.domain;

import movie.domain.ReserveCanceled;
import movie.domain.MovieReserved;
import movie.ReservationApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Reservation_table")
@Data

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private Long movieId;

    private Long screenId;

    private Long customerId;

    private Long paymentId;

    private Integer seatingQty;

    private String customerEmail;

    private String theaterName;

    private Date screenDate;

    @PostPersist
    public void onPostPersist() {
        
        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        movie.external.Payment payment = new movie.external.Payment();
        // mappings goes here
        payment.setAmount(this.getSeatingQty() * 100000);
        payment.setReserveId(this.getId());
        payment.setPayStatus("APPR");
        payment.setCustomerId(this.getCustomerId());

        ReservationApplication.applicationContext
            .getBean(movie.external.PaymentService.class)
            .approvePay(payment);

        MovieReserved movieReserved = new MovieReserved(this);
        movieReserved.publishAfterCommit();
        // Get request from Screen
        //movie.external.Screen screen =
        //    Application.applicationContext.getBean(movie.external.ScreenService.class)
        //    .getScreen(/** mapping value needed */);

    }

    @PreRemove
    public void onPreRemove() {
        ReserveCanceled reserveCanceled = new ReserveCanceled(this);
        reserveCanceled.publishAfterCommit();
    }

    public static ReservationRepository repository() {
        ReservationRepository reservationRepository = ReservationApplication.applicationContext.getBean(
            ReservationRepository.class
        );
        return reservationRepository;
    }

    public static void sendMsgScreen(ScreenUpdated screenUpdated) {

        System.out.println("###sendMsg#############################################");
        System.out.println("죄송합니다.");
        System.out.println("죄송합니다. 상영시간이 1시간 연기되었습니다.");
        System.out.println("#######################################################");

        /**
         * Example 1: new item
         * Reservation reservation = new Reservation();
         * repository().save(reservation);
         */

        /**
         * Example 2: finding and process
         * 
         * repository().findById(screenUpdated.get???()).ifPresent(reservation->{
         * 
         * reservation // do something
         * repository().save(reservation);
         * 
         * });
         */

    }

    @PostLoad
    public void makeDelay(){
        try {
            Thread.currentThread().sleep((long) (10000 + Math.random() * 220));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
