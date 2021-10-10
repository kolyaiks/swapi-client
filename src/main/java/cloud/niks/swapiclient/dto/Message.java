package cloud.niks.swapiclient.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    private String message;
    private Report report;
    private Characters characters;
    private Starships starships;

    @Getter
    @Setter
    @Builder
    public static class Report {
        List<ReportRow> reportRows;
    }

    @Getter
    @Setter
    @Builder
    public static class Characters {
        List<CharacterDto> charactersRows;
    }

    @Getter
    @Setter
    @Builder
    public static class Starships {
        List<StarshipDto> starshipsRows;
    }

}
