package cloud.niks.swapiclient.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class Client {

    private final RestTemplate restTemplate;

    public String sendRequest(String url) {
        return restTemplate.getForObject(url, String.class);
    }
}
