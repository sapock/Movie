package movie.infra;

import movie.domain.*;
import movie.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MovieRatingInfoViewHandler {

    @Autowired
    private MovieRatingInfoRepository movieRatingInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRatingRegistered_then_CREATE_1 (@Payload RatingRegistered ratingRegistered) {
        try {

            if (!ratingRegistered.validate()) return;

            // view 객체 생성
            MovieRatingInfo movieRatingInfo = new MovieRatingInfo();
            // view 객체에 이벤트의 Value 를 set 함
            movieRatingInfo.setMovieId(ratingRegistered.getMovieId());
            movieRatingInfo.setCustomerId(ratingRegistered.getCustomerId());
            movieRatingInfo.setScore(ratingRegistered.getScore());
            movieRatingInfo.setReview(ratingRegistered.getReview());
            movieRatingInfo.setId(ratingRegistered.getId());
            // view 레파지 토리에 save
            movieRatingInfoRepository.save(movieRatingInfo);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenRatingDeleted_then_UPDATE_1(@Payload RatingDeleted ratingDeleted) {
        try {
            if (!ratingDeleted.validate()) return;
                // view 객체 조회

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRatingDeleted_then_DELETE_1(@Payload RatingDeleted ratingDeleted) {
        try {
            if (!ratingDeleted.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            movieRatingInfoRepository.deleteById(ratingDeleted.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

