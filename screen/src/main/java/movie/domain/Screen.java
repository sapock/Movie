package movie.domain;

import movie.domain.ScreenUpdated;
import movie.ScreenApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;


@Entity
@Table(name="Screen_table")
@Data

public class Screen  {


    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    
    
    
    
    
    private Long id;
    
    
    
    
    
    private Long movieId;
    
    
    
    
    
    private String theaterName;
    
    
    
    
    
    private Date screenDate;
    
    
    
    
    
    private Integer seatingCapacity;
    
    
    
    
    
    private Integer seatingRemain;
    
    
    
    
    
    private Integer seatingPrice;

    @PostPersist
    public void onPostPersist(){


        ScreenUpdated screenUpdated = new ScreenUpdated(this);
        screenUpdated.publishAfterCommit();

    }

    public static ScreenRepository repository(){
        ScreenRepository screenRepository = ScreenApplication.applicationContext.getBean(ScreenRepository.class);
        return screenRepository;
    }




    public static void reduceSeating(MovieReserved movieReserved){

        /** Example 1:  new item 
        Screen screen = new Screen();
        repository().save(screen);

        */

        /** Example 2:  finding and process
        
        repository().findById(movieReserved.get???()).ifPresent(screen->{
            
            screen // do something
            repository().save(screen);


         });
        */

        
    }
    public static void addSeating(ReserveCanceled reserveCanceled){

        /** Example 1:  new item 
        Screen screen = new Screen();
        repository().save(screen);

        */

        /** Example 2:  finding and process
        
        repository().findById(reserveCanceled.get???()).ifPresent(screen->{
            
            screen // do something
            repository().save(screen);


         });
        */

        
    }


}
