package cloud.niks.swapiclient.controller;

import cloud.niks.swapiclient.dto.Message;
import cloud.niks.swapiclient.dto.ReportRow;
import cloud.niks.swapiclient.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/report")
    public Message getReport() {
        List<ReportRow> report = reportService.getReport();
        return Message.builder()
                .report(Message.Report.builder()
                        .reportRows(report)
                        .build())
                .build();
    }

}
