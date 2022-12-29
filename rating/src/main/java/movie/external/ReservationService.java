package movie.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;


@FeignClient(name = "reservation", url = "${api.url.reservation}")
public interface ReservationService {
    @RequestMapping(method= RequestMethod.GET, path="/reservations/{id}")
    public Reservation getReservation(@PathVariable("id") Long id);
}
