import com.fasterxml.jackson.databind.ObjectMapper;
import controller.ApplicationController;
import controller.Dispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public Dispatcher dispatcher() { return new Dispatcher(); }

    @Bean
    public ApplicationController applicationController() {
        return new ApplicationController(dispatcher());
    }

}
