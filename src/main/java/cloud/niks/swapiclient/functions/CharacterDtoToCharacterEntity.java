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
    public Character apply(CharacterDto character) {
        return Character.builder()
                .url(character.getUrl())
                .name(character.getName())
                .gender(character.getGender())
                .homeworld(planetRepository.getById(character.getHomeworld()))
                .build();
    }

}
