package movie.infra;

import movie.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;

@RepositoryRestResource(collectionResourceRel="movieRatingInfos", path="movieRatingInfos")
public interface MovieRatingInfoRepository extends PagingAndSortingRepository<MovieRatingInfo, Long> {

    

    
}
