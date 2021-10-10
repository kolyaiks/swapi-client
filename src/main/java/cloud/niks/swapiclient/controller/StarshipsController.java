package cloud.niks.swapiclient.controller;

import cloud.niks.swapiclient.dto.Message;
import cloud.niks.swapiclient.dto.StarshipDto;
import cloud.niks.swapiclient.service.StarshipsService;
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
@RequestMapping("/starships")
@RequiredArgsConstructor
public class StarshipsController {

    private final StarshipsService starshipsService;

    @GetMapping
    public Message getAll() {
        List<StarshipDto> starshipDtos = starshipsService.getAll();
        return Message.builder()
                .starships(Message.Starships.builder()
                        .starshipsRows(starshipDtos)
                        .build())
                .build();
    }

    @GetMapping("/{id}")
    public StarshipDto getById(@PathVariable Integer id) throws Exception {
        String fullStarshipUrl = String.format("https://swapi.dev/api/starships/%s/", id);
        return starshipsService.getById(fullStarshipUrl);
    }

    @PostMapping("/update")
    public Message update(@Valid @RequestBody StarshipDto starshipDto) {
        return Message.builder()
                .message(starshipsService.update(starshipDto))
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
