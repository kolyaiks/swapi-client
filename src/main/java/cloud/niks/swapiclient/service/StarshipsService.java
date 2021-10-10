package cloud.niks.swapiclient.service;

import cloud.niks.swapiclient.client.Client;
import cloud.niks.swapiclient.dto.StarshipDto;
import cloud.niks.swapiclient.dto.Starships;
import cloud.niks.swapiclient.functions.StarshipDtoToStarshipEntity;
import cloud.niks.swapiclient.functions.StarshipEntityToStarshipDto;
import cloud.niks.swapiclient.properties.AppProperties;
import cloud.niks.swapiclient.storage.entity.Starship;
import cloud.niks.swapiclient.storage.repository.StarshipRepository;
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
public class StarshipsService {

    private final AppProperties appProperties;
    private final Client client;
    private final ObjectMapper objectMapper;
    private final StarshipRepository starshipRepository;
    private final StarshipEntityToStarshipDto starshipEntityToStarshipDto;
    private final StarshipDtoToStarshipEntity starshipDtoToStarshipEntity;

    @SneakyThrows
    public List<StarshipDto> downloadFromSwapi() {
        log.info("Start of getting starships..");

        List<StarshipDto> starshipsList = new ArrayList<>();
        String url = appProperties.getSwapiURL().getStarships();

        while (Objects.nonNull(url)) {
            log.info(String.format("Parsing page %s ..", url));
            String response = client.sendRequest(url);
            Starships starships = objectMapper.readValue(response, Starships.class);
            starshipsList.addAll(starships.getResults());
            url = starships.getNext();
            if (Objects.isNull(url)) log.info("There is no next page to parse for new starships.");
        }
        return starshipsList;
    }

    public List<Starship> buildEntities(List<StarshipDto> dtos) {
        log.info("Start of converting Starship DTOs to Starship Entities..");
        List<Starship> starships = new ArrayList<>();

        for (StarshipDto element : dtos) {
            Starship starship = starshipDtoToStarshipEntity.apply(element);
            starships.add(starship);
        }
        log.info("Starships conversion finished.");
        return starships;
    }

    public List<StarshipDto> getAll() {
        return starshipRepository.findAll().stream()
                .map(starship -> starshipEntityToStarshipDto.apply(starship)
                ).collect(Collectors.toList());
    }

    public StarshipDto getById(String id) throws Exception {
        Optional<Starship> starship = starshipRepository.findById(id);
        if (starship.isPresent()) {
            return starshipEntityToStarshipDto.apply(starship.get());
        } else {
            throw new Exception(String.format("Element %s not found!", id));
        }
    }

    public String update(StarshipDto starshipDto) {
        starshipRepository.save(starshipDtoToStarshipEntity.apply(starshipDto));
        return "Updated";
    }

}
