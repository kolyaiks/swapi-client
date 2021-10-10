package cloud.niks.swapiclient.functions;

import cloud.niks.swapiclient.dto.CharacterDto;
import cloud.niks.swapiclient.storage.entity.Character;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CharacterEntityToCharacterDto implements Function<Character, CharacterDto> {

    @Override
    public CharacterDto apply(Character character) {
        return CharacterDto.builder()
                .name(character.getName())
                .gender(character.getGender())
                .homeworld(character.getHomeworld().getUrl())
                .url(character.getUrl())
                .starships(character.getStarships().stream()
                        .map(starship -> starship.getUrl()).collect(Collectors.toSet()))
                .build();
    }
}
