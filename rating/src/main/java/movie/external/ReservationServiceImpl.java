package movie.external;

import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    /**
     * Fallback
     */
    public Reservation getReservation(Long id) {
        Reservation fallbackValue = new Reservation();
        fallbackValue.setId(1L);
        return fallbackValue;
    }
}
