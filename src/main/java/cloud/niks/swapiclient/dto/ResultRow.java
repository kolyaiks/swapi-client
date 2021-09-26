package cloud.niks.swapiclient.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResultRow {

    private String starshipId;
    private String starshipName;
    private Long cargoCapacity;
    private String characterId;
    private String characterName;

}
