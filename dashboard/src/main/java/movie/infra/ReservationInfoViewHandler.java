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
public class ReservationInfoViewHandler {

    @Autowired
    private ReservationInfoRepository reservationInfoRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMovieReserved_then_CREATE_1 (@Payload MovieReserved movieReserved) {
        try {

            if (!movieReserved.validate()) return;

            // view 객체 생성
            ReservationInfo reservationInfo = new ReservationInfo();
            // view 객체에 이벤트의 Value 를 set 함
            reservationInfo.setReservId(movieReserved.getId());
            reservationInfo.setSeatingQty(movieReserved.getSeatingQty());
            reservationInfo.setScreenId(movieReserved.getScreenId());
            reservationInfo.setScreenDate(movieReserved.getScreenDate());
            // view 레파지 토리에 save
            reservationInfoRepository.save(reservationInfo);

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @StreamListener(KafkaProcessor.INPUT)
    public void whenReserveCanceled_then_DELETE_1(@Payload ReserveCanceled reserveCanceled) {
        try {
            if (!reserveCanceled.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            reservationInfoRepository.deleteById(reserveCanceled.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

