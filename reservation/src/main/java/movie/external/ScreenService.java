package movie.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;


@FeignClient(name = "screen", url = "${api.url.screen}")
public interface ScreenService {
    @RequestMapping(method= RequestMethod.GET, path="/screens/{id}")
    public Screen getScreen(@PathVariable("id") Long id);
}
