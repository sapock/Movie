package movie.common;


import movie.ScreenApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(classes = { ScreenApplication.class })
public class CucumberSpingConfiguration {
    
}
