package movie.infra;

import movie.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="reservationInfos", path="reservationInfos")
public interface ReservationInfoRepository extends PagingAndSortingRepository<ReservationInfo, Long> {

    

    
}
