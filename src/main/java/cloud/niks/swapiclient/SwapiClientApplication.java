package cloud.niks.swapiclient;

import cloud.niks.swapiclient.service.InitDatasetService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class SwapiClientApplication implements CommandLineRunner {

    private final InitDatasetService initDatasetService;

    public static void main(String[] args) {
        SpringApplication.run(SwapiClientApplication.class, args);
    }

    @Override
    public void run(String... args) {
        initDatasetService.initDataset();
    }
}
