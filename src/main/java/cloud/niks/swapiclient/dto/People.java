package cloud.niks.swapiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class People {

    private int count;
    private String next;
    private String previous;
    private List<Character> results;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Character {

        private String name;
        private String height;
        private String mass;
        @JsonProperty("hair_color")
        private String hairColor;
        @JsonProperty("skin_color")
        private String skinColor;
        @JsonProperty("eye_color")
        private String eyeColor;
        @JsonProperty("birth_year")
        private String birthYear;
        private String gender;
        private String homeworld;
        private List<String> films;
        private List<String> species;
        private List<String> vehicles;
        private List<String> starships;
        private Date created;
        private Date edited;
        private String url;

    }
}
