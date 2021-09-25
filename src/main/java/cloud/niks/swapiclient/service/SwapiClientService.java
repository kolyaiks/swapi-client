package cloud.niks.swapiclient.service;

import cloud.niks.swapiclient.client.SwapiClient;
import cloud.niks.swapiclient.dto.People;
import cloud.niks.swapiclient.dto.Planets;
import cloud.niks.swapiclient.dto.Starships;
import cloud.niks.swapiclient.entity.Character;
import cloud.niks.swapiclient.entity.Starship;
import cloud.niks.swapiclient.properties.AppProperties;
import cloud.niks.swapiclient.repository.CharacterRepository;
import cloud.niks.swapiclient.repository.StarshipRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class SwapiClientService {

    private final AppProperties appProperties;
    private final SwapiClient swapiClient;
    private final ObjectMapper objectMapper;
    private final StarshipRepository starshipRepository;
    private final CharacterRepository characterRepository;

    public void run() {

        List<Planets.Planet> planets = getPlanets();
        List<People.Character> characters = getCharacters();
        uploadCharactersToDB(characters, planets);
        List<Starships.Starship> starships = getStarships();
        uploadStarshipsToDB(starships);

//        Comparator<Starships.Starship> capacityComporator = (s1, s2) -> Long.valueOf(s2.getCargoCapacity()).compareTo(Long.valueOf(s1.getCargoCapacity()));
//        List<Starships.Starship> topStarships = starships.stream()
//                .filter(starship -> !"unknown".equals(starship.getCargoCapacity()))
//                .sorted(capacityComporator)
//                .limit(10)
//                .collect(Collectors.toList());
        System.out.printf("stop");
    }

    @SneakyThrows
    public List<Planets.Planet> getPlanets() {
        log.info("Start of getting planets..");
        List<Planets.Planet> planetsList = new ArrayList<>();
        String url = appProperties.getSwapiURL().getPlanets();
        while (Objects.nonNull(url)) {
            log.info(String.format("Parsing page %s ..", url));
            String response = swapiClient.sendRequestToSwapi(url);
            Planets planetsDto = objectMapper.readValue(response, Planets.class);
            planetsList.addAll(planetsDto.getResults());
            url = planetsDto.getNext();
            if (Objects.isNull(url)) log.info("There is no next page to parse");
        }
        return planetsList;
    }

    @SneakyThrows
    public List<People.Character> getCharacters() {
        log.info("Start of getting characters..");
        List<People.Character> characters = new ArrayList<>();
        String url = appProperties.getSwapiURL().getPeople();
        while (Objects.nonNull(url)) {
            log.info(String.format("Parsing page %s ..", url));
            String response = swapiClient.sendRequestToSwapi(url);
            People people = objectMapper.readValue(response, People.class);
            characters.addAll(people.getResults());
            url = people.getNext();
            if (Objects.isNull(url)) log.info("There is no next page to parse");
        }
        return characters;
    }

    @SneakyThrows
    public List<Starships.Starship> getStarships() {
        log.info("Start of getting starships..");
        List<Starships.Starship> starshipsList = new ArrayList<>();
        String url = appProperties.getSwapiURL().getStarships();
        while (Objects.nonNull(url)) {
            log.info(String.format("Parsing page %s ..", url));
            String response = swapiClient.sendRequestToSwapi(url);
            Starships starships = objectMapper.readValue(response, Starships.class);
            starshipsList.addAll(starships.getResults());
            url = starships.getNext();
            if (Objects.isNull(url)) log.info("There is no next page to parse");
        }
        return starshipsList;
    }

    private void uploadCharactersToDB(List<People.Character> characters, List<Planets.Planet> planets) {
        for (People.Character element : characters) {
            Character character = Character.builder()
                    .url(element.getUrl())
                    .name(element.getName())
                    .gender(element.getGender())
                    .homeworld(findPlanetName(planets, element.getHomeworld()))
                    .build();
            characterRepository.save(character);
        }
    }

    private String findPlanetName(List<Planets.Planet> planets, String planetUrlInCharacterDescription) {
        return planets.stream().sequential()
                .filter(planet -> planet.getUrl().equals(planetUrlInCharacterDescription))
                .findFirst()
                .get()
                .getName();
    }

    private void uploadStarshipsToDB(List<Starships.Starship> starships) {
        for (Starships.Starship element : starships) {
            Starship starship = Starship.builder()
                    .url(element.getUrl())
                    .name(element.getName())
                    .model(element.getModel())
                    .manufacturer(element.getManufacturer())
                    .cargoCapacity("unknown".equals(element.getCargoCapacity()) ? 0 : Long.valueOf(element.getCargoCapacity()))
                    .pilots(findCharactersEntities(element))
                    .build();
            starshipRepository.save(starship);
        }
    }

    private Set<Character> findCharactersEntities(Starships.Starship starshipDto) {
        Set<String> pilots = starshipDto.getPilots();
        Set<Character> charactersEntities = pilots.stream().sequential()
                .map(pilot -> characterRepository.findById(pilot).get())
                .collect(Collectors.toSet());
        return charactersEntities;
    }


}
