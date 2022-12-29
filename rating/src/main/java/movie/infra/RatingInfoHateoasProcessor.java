package movie.infra;
import movie.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class RatingInfoHateoasProcessor implements RepresentationModelProcessor<EntityModel<RatingInfo>>  {

    @Override
    public EntityModel<RatingInfo> process(EntityModel<RatingInfo> model) {

        
        return model;
    }
    
}
