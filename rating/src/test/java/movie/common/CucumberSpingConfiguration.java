package movie.common;


import movie.RatingApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { RatingApplication.class })
public class CucumberSpingConfiguration {
    
}
