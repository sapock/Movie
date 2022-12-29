package movie.common;


import movie.MovieApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { MovieApplication.class })
public class CucumberSpingConfiguration {
    
}
