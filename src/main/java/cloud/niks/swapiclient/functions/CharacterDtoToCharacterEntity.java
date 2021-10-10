package cloud.niks.swapiclient.functions;

import cloud.niks.swapiclient.dto.CharacterDto;
import cloud.niks.swapiclient.storage.entity.Character;
import cloud.niks.swapiclient.storage.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class CharacterDtoToCharacterEntity implements Function<CharacterDto, Character> {

    private final PlanetRepository planetRepository;

    @Override
    public Character apply(CharacterDto characterDto) {
        return Character.builder()
                .url(characterDto.getUrl())
                .name(characterDto.getName())
                .gender(characterDto.getGender())
                .homeworld(planetRepository.findById(characterDto.getHomeworld())
                        .orElseThrow(() -> new RuntimeException(String.format("No value %s found in the database",
                                characterDto.getHomeworld()))))
                .build();
    }

}
