package cloud.niks.swapiclient.service;

import cloud.niks.swapiclient.client.Client;
import cloud.niks.swapiclient.dto.CharacterDto;
import cloud.niks.swapiclient.dto.PlanetDto;
import cloud.niks.swapiclient.dto.Planets;
import cloud.niks.swapiclient.dto.StarshipDto;
import cloud.niks.swapiclient.functions.CharacterDtoToCharacterEntity;
import cloud.niks.swapiclient.functions.PlanetDtoToPlanetEntity;
import cloud.niks.swapiclient.functions.StarshipDtoToStarshipEntity;
import cloud.niks.swapiclient.properties.AppProperties;
import cloud.niks.swapiclient.storage.entity.Character;
import cloud.niks.swapiclient.storage.entity.Planet;
import cloud.niks.swapiclient.storage.entity.Starship;
import cloud.niks.swapiclient.storage.repository.CharacterRepository;
import cloud.niks.swapiclient.storage.repository.PlanetRepository;
import cloud.niks.swapiclient.storage.repository.StarshipRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class InitDatasetService {

    private final CharacterRepository characterRepository;
    private final StarshipRepository starshipRepository;
    private final PlanetRepository planetRepository;
    private final CharacterService characterService;
    private final StarshipsService starshipsService;
    private final PlanetService planetService;

    public String initDataset() {

        List<PlanetDto> planetDtos = planetService.downloadFromSwapi();
        List<Planet> planets = planetService.buildEntities(planetDtos);
        planetRepository.saveAll(planets);

        List<CharacterDto> charactersDtos = characterService.downloadFromSwapi();
        List<Character> characters = characterService.buildEntities(charactersDtos);
        characterRepository.saveAll(characters);

        List<StarshipDto> starshipsDtos = starshipsService.downloadFromSwapi();
        List<Starship> starships = starshipsService.buildEntities(starshipsDtos);
        starshipRepository.saveAll(starships);

        return "Dataset is initiated";
    }

}
