package movie.domain;

import movie.MovieApplication;
import javax.persistence.*;
import java.util.List;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "Movie_table")
@Data

public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private String subject;

    private String contents;

    private Float avgScore;

    private Integer voteCnt;

    @PostPersist
    public void onPostPersist() {
    }

    public static MovieRepository repository() {
        MovieRepository movieRepository = MovieApplication.applicationContext.getBean(MovieRepository.class);
        return movieRepository;
    }

    public static void registRatingScore(RatingRegistered ratingRegistered) {

        /**
         * Example 1: new item
         * Movie movie = new Movie();
         * repository().save(movie);
         * 
         */

        // ** Example 2: finding and process

        repository().findById(Long.valueOf(ratingRegistered.getMovieId())).ifPresent(movie -> {
            // movie // do something
            movie.setAvgScore(movie.getAvgScore() * movie.getVoteCnt()
                    + (ratingRegistered.getScore()) / (movie.getVoteCnt() + 1));
            movie.setVoteCnt(movie.getVoteCnt() + 1);
            repository().save(movie);
        });
        // */

    }

    public static void deleteRatingScore(RatingDeleted ratingDeleted) {

        repository().findById(Long.valueOf(ratingDeleted.getMovieId())).ifPresent(movie -> {
            // movie // do something
            if ((movie.getVoteCnt() - 1) > 0) {
                movie.setAvgScore((movie.getAvgScore() * movie.getVoteCnt()
                        - ratingDeleted.getScore()) / (movie.getVoteCnt() - 1));
            } else {
                movie.setAvgScore((float)(0.0));
            }
            movie.setVoteCnt(movie.getVoteCnt() - 1);
            repository().save(movie);
        });
    }

}
