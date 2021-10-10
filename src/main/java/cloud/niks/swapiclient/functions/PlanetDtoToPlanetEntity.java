package cloud.niks.swapiclient.functions;

import cloud.niks.swapiclient.dto.PlanetDto;
import cloud.niks.swapiclient.storage.entity.Planet;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PlanetDtoToPlanetEntity implements Function<PlanetDto, Planet> {

    @Override
    public Planet apply(PlanetDto planetDto) {
        return Planet.builder()
                .url(planetDto.getUrl())
                .name(planetDto.getName())
                .build();
    }
}
