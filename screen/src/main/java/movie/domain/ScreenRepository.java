package movie.domain;

import movie.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel="screens", path="screens")
public interface ScreenRepository extends PagingAndSortingRepository<Screen, Long>{

}
