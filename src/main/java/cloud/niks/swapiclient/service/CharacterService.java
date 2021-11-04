package cloud.niks.swapiclient.service;

import cloud.niks.swapiclient.client.Client;
import cloud.niks.swapiclient.dto.CharacterDto;
import cloud.niks.swapiclient.dto.People;
import cloud.niks.swapiclient.functions.CharacterDtoToCharacterEntity;
import cloud.niks.swapiclient.functions.CharacterEntityToCharacterDto;
import cloud.niks.swapiclient.properties.AppProperties;
import cloud.niks.swapiclient.storage.entity.Character;
import cloud.niks.swapiclient.storage.repository.CharacterRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CharacterService {

    private final AppProperties appProperties;
    private final Client client;
    private final ObjectMapper objectMapper;
    private final CharacterRepository characterRepository;
    private final CharacterEntityToCharacterDto characterEntityToCharacterDto;
    private final CharacterDtoToCharacterEntity characterDtoToCharacterEntity;

    @SneakyThrows
    public List<CharacterDto> downloadFromSwapi() {
        log.info("Start of getting characters..");
        List<CharacterDto> characters = new ArrayList<>();

        String url = appProperties.getSwapiURL().getPeople();

        while (Objects.nonNull(url)) {
            log.info(String.format("Parsing page %s ..", url));
            String response = client.sendRequest(url);
            People people = objectMapper.readValue(response, People.class);
            characters.addAll(people.getResults());
            url = people.getNext();
            if (Objects.isNull(url)) log.info("There is no next page to parse for new characters.");
        }
        return characters;
    }

    public List<Character> buildEntities(List<CharacterDto> dtos) {
        log.info("Start of converting Characters DTOs to Characters Entities..");
        List<Character> characters = new ArrayList<>();

        for (CharacterDto element : dtos) {
            Character character = characterDtoToCharacterEntity.apply(element);
            characters.add(character);
        }
        log.info("Characters conversion finished.");
        return characters;
    }

    public List<CharacterDto> getAll() {
        return characterRepository.findAll().stream()
                .map(character -> characterEntityToCharacterDto.apply(character)
                ).collect(Collectors.toList());
    }

    public CharacterDto getById(String id) throws Exception {
        Optional<Character> character = characterRepository.findById(id);
        if (character.isPresent()) {
            return characterEntityToCharacterDto.apply(character.get());
        } else {
            throw new Exception(String.format("Element %s not found!", id));
        }
    }

    public String update(CharacterDto characterDto) {
        characterRepository.save(characterDtoToCharacterEntity.apply(characterDto));
        return "Updated";
    }

}
