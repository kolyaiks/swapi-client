package cloud.niks.swapiclient.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HelperClasses {

    @Bean
    public ObjectMapper buildObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestTemplate buildRestTemplate() {
        return new RestTemplate();
    }

}
