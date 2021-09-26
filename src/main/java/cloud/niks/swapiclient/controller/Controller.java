package cloud.niks.swapiclient.controller;

import cloud.niks.swapiclient.dto.ResultRow;
import cloud.niks.swapiclient.service.GetDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final GetDataService getDataService;

    @GetMapping("/getData")
    public List<ResultRow> getData() {
        List<ResultRow> data = getDataService.getData();
        return data;
    }


}
