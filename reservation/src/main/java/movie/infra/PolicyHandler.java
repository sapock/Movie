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
    @Autowired ReservationRepository reservationRepository;
    
    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}

    @StreamListener(value=KafkaProcessor.INPUT, condition="headers['type']=='ScreenUpdated'")
    public void wheneverScreenUpdated_SendMsgScreen(@Payload ScreenUpdated screenUpdated){

        ScreenUpdated event = screenUpdated;
        System.out.println("\n\n##### listener SendMsgScreen : " + screenUpdated + "\n\n");


        // Comments // 
		//영화정보(상영시간등) 변경 시, 알림

        // Sample Logic //
        Reservation.sendMsgScreen(event);
        

        

    }

}


