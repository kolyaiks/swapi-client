package cloud.niks.swapiclient.controller;

import cloud.niks.swapiclient.dto.Message;
import cloud.niks.swapiclient.service.InitDatasetService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InitController {

    private final InitDatasetService initDatasetService;

    @GetMapping("/init")
    public Message initDataset() {
        return Message.builder()
                .message(initDatasetService.initDataset())
                .build();
    }

}
