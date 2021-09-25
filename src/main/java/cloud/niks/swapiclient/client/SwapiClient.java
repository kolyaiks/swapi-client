package cloud.niks.swapiclient.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class SwapiClient {

    private final RestTemplate restTemplate;

    public String sendRequestToSwapi(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
