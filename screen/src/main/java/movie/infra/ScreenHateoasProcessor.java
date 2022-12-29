package movie.infra;
import movie.domain.*;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class ScreenHateoasProcessor implements RepresentationModelProcessor<EntityModel<Screen>>  {

    @Override
    public EntityModel<Screen> process(EntityModel<Screen> model) {

        
        return model;
    }
    
}
