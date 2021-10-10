package cloud.niks.swapiclient.functions;

import cloud.niks.swapiclient.dto.PlanetDto;
import cloud.niks.swapiclient.storage.entity.Planet;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PlanetEntityToPlanetDto implements Function<Planet, PlanetDto> {

    @Override
    public PlanetDto apply(Planet planet) {
        return PlanetDto.builder()
                .name(planet.getName())
                .url(planet.getUrl())
                .build();
    }
}
