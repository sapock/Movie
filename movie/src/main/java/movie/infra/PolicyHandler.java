package movie.infra;

import javax.naming.NameParser;

import javax.naming.NameParser;
import javax.transaction.Transactional;

import movie.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import movie.domain.*;

@Service
@Transactional
public class PolicyHandler{
    @Autowired MovieRepository movieRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='RatingRegistered'")
    public void wheneverRatingRegistered_RegistRatingScore(@Payload RatingRegistered ratingRegistered){

        RatingRegistered event = ratingRegistered;
        System.out.println("\n\n##### listener RegistRatingScore : " + ratingRegistered + "\n\n");


        

        // Sample Logic //
        Movie.registRatingScore(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='RatingDeleted'")
    public void wheneverRatingDeleted_DeleteRatingScore(@Payload RatingDeleted ratingDeleted){

        RatingDeleted event = ratingDeleted;
        System.out.println("\n\n##### listener DeleteRatingScore : " + ratingDeleted + "\n\n");


        

        // Sample Logic //
        Movie.deleteRatingScore(event);
        

        

    }

}


