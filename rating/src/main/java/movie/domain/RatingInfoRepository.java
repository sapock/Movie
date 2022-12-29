package movie.domain;

import movie.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="ratingInfos", path="ratingInfos")
public interface RatingInfoRepository extends PagingAndSortingRepository<RatingInfo, Long>{

}
