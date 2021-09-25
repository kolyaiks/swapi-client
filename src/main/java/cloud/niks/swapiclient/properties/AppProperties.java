package cloud.niks.swapiclient.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties
@Component
@Getter
@Setter
public class AppProperties {

    private SwapiURL swapiURL;

    @Getter
    @Setter
    public static class SwapiURL {
        private String planets;
        private String people;
        private String starships;
    }
}
