package cloud.niks.swapiclient.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ReportRow {

    private String characterId;
    private String characterName;
    private String characterGender;
    private String characterHomeworld;
    private String starshipId;
    private String starshipName;
    private Long cargoCapacity;
    private String model;
    private String manufacturer;


}
