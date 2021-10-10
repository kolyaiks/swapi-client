package cloud.niks.swapiclient.functions;

import cloud.niks.swapiclient.dto.StarshipDto;
import cloud.niks.swapiclient.storage.entity.Starship;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class StarshipEntityToStarshipDto implements Function<Starship, StarshipDto> {

    @Override
    public StarshipDto apply(Starship starship) {
        return StarshipDto.builder()
                .name(starship.getName())
                .cargoCapacity(String.valueOf(starship.getCargoCapacity()))
                .model(starship.getModel())
                .manufacturer(starship.getManufacturer())
                .url(starship.getUrl())
                .pilots(starship.getPilots().stream()
                        .map(pilot -> pilot.getUrl()).collect(Collectors.toSet())
                )
                .build();
    }

}
