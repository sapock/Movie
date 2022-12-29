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
    @Autowired ScreenRepository screenRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='MovieReserved'")
    public void wheneverMovieReserved_ReduceSeating(@Payload MovieReserved movieReserved){

        MovieReserved event = movieReserved;
        System.out.println("\n\n##### listener ReduceSeating : " + movieReserved + "\n\n");


        

        // Sample Logic //
        Screen.reduceSeating(event);
        

        

    }

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='ReserveCanceled'")
    public void wheneverReserveCanceled_AddSeating(@Payload ReserveCanceled reserveCanceled){

        ReserveCanceled event = reserveCanceled;
        System.out.println("\n\n##### listener AddSeating : " + reserveCanceled + "\n\n");


        

        // Sample Logic //
        Screen.addSeating(event);
        

        

    }

}


