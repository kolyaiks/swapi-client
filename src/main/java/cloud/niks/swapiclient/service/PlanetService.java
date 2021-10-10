package cloud.niks.swapiclient.service;

import cloud.niks.swapiclient.client.Client;
import cloud.niks.swapiclient.dto.PlanetDto;
import cloud.niks.swapiclient.dto.Planets;
import cloud.niks.swapiclient.functions.PlanetDtoToPlanetEntity;
import cloud.niks.swapiclient.functions.PlanetEntityToPlanetDto;
import cloud.niks.swapiclient.properties.AppProperties;
import cloud.niks.swapiclient.storage.entity.Planet;
import cloud.niks.swapiclient.storage.repository.PlanetRepository;
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
public class PlanetService {

    private final AppProperties appProperties;
    private final Client client;
    private final ObjectMapper objectMapper;
    private final PlanetDtoToPlanetEntity planetDtoToPlanetEntity;
    private final PlanetEntityToPlanetDto planetEntityToPlanetDto;
    private final PlanetRepository planetRepository;

    @SneakyThrows
    public List<PlanetDto> downloadFromSwapi() {
        log.info("Start of getting planets..");
        List<PlanetDto> planetsDto = new ArrayList<>();

        String url = appProperties.getSwapiURL().getPlanets();

        while (Objects.nonNull(url)) {
            log.info(String.format("Parsing page %s ..", url));
            String response = client.sendRequest(url);
            Planets planets = objectMapper.readValue(response, Planets.class);
            planetsDto.addAll(planets.getResults());
            url = planets.getNext();
            if (Objects.isNull(url)) log.info("There is no next page to parse for new planets.");
        }
        return planetsDto;
    }

    public List<Planet> buildEntities(List<PlanetDto> dtos) {
        log.info("Start of converting Planets DTOs to Planets Entities..");
        List<Planet> planets = new ArrayList<>();

        for (PlanetDto element : dtos) {
            Planet planet = planetDtoToPlanetEntity.apply(element);
            planets.add(planet);
        }
        log.info("Planets conversion finished.");
        return planets;
    }

    public List<PlanetDto> getAll() {
        return planetRepository.findAll().stream()
                .map(planet -> planetEntityToPlanetDto.apply(planet)
                ).collect(Collectors.toList());
    }

    public PlanetDto getById(String id) throws Exception {
        Optional<Planet> planet = planetRepository.findById(id);
        if (planet.isPresent()) {
            return planetEntityToPlanetDto.apply(planet.get());
        } else {
            throw new Exception(String.format("Element %s not found!", id));
        }
    }

    public String update(PlanetDto dto) {
        planetRepository.save(planetDtoToPlanetEntity.apply(dto));
        return "Updated";
    }

}
