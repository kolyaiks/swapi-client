package cloud.niks.swapiclient.functions;

import cloud.niks.swapiclient.dto.StarshipDto;
import cloud.niks.swapiclient.storage.entity.Character;
import cloud.niks.swapiclient.storage.entity.Starship;
import cloud.niks.swapiclient.storage.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StarshipDtoToStarshipEntity implements Function<StarshipDto, Starship> {

    private final CharacterRepository characterRepository;

    @Override
    public Starship apply(StarshipDto starshipDto) {
        return Starship.builder()
                .url(starshipDto.getUrl())
                .name(starshipDto.getName())
                .cargoCapacity("unknown".equals(starshipDto.getCargoCapacity()) ? 0 : Long.valueOf(starshipDto.getCargoCapacity()))
                .model(starshipDto.getModel())
                .manufacturer(starshipDto.getManufacturer())
                .pilots(findCharactersEntities(starshipDto.getPilots()))
                .build();
    }

    public Set<Character> findCharactersEntities(Set<String> pilots) {
        if (Objects.isNull(pilots)) return null;

        return pilots.stream().sequential()
                .map(pilot -> characterRepository.findById(pilot).get())
                .collect(Collectors.toSet());
    }


}
