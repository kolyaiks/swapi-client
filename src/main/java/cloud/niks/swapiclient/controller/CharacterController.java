package cloud.niks.swapiclient.controller;


import cloud.niks.swapiclient.dto.CharacterDto;
import cloud.niks.swapiclient.dto.Message;
import cloud.niks.swapiclient.service.CharacterService;
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
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping
    public Message getAll() {
        List<CharacterDto> characterDtos = characterService.getAll();
        return Message.builder()
                .characters(Message.Characters.builder()
                        .charactersRows(characterDtos)
                        .build())
                .build();
    }

    @GetMapping("/{id}")
    public CharacterDto getById(@PathVariable Integer id) throws Exception {
        String fullCharacterUrl = String.format("https://swapi.dev/api/people/%s/", id);
        return characterService.getById(fullCharacterUrl);
    }

    @PostMapping("/update")
    public Message update(@Valid @RequestBody CharacterDto characterDto) {
        return Message.builder()
                .message(characterService.update(characterDto))
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
