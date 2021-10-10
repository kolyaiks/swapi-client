package cloud.niks.swapiclient.controller;

import cloud.niks.swapiclient.dto.Message;
import cloud.niks.swapiclient.dto.PlanetDto;
import cloud.niks.swapiclient.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/planets")
@RequiredArgsConstructor
public class PlanetController {

    private final PlanetService planetService;

    @GetMapping
    public List<PlanetDto> getAll() {
        return planetService.getAll();
    }

    @GetMapping("/{id}")
    public PlanetDto getById(@PathVariable Integer id) throws Exception {
        String fullPlanetUrl = String.format("https://swapi.dev/api/planets/%s/", id);
        return planetService.getById(fullPlanetUrl);
    }

    @PostMapping("/update")
    public Message update(@Valid @RequestBody PlanetDto planetDto) {
        return Message.builder()
                .message(planetService.update(planetDto))
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Message allExceptionsHandler(Exception e) {
        return Message.builder()
                .message(e.getMessage())
                .build();
    }

}
