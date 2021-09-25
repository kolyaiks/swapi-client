package cloud.niks.swapiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Starships {
    private int count;
    private String next;
    private String previous;
    private List<Starship> results;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Starship {
        private String name;
        private String model;
        private String manufacturer;
        @JsonProperty("cost_in_credits")
        private String costInCredits;
        private String length;
        @JsonProperty("max_atmosphering_speed")
        private String maxAtmospheringSpeed;
        private String crew;
        private String passengers;
        @JsonProperty("cargo_capacity")
        private String cargoCapacity;
        private String consumables;
        @JsonProperty("hyperdrive_rating")
        private String hyperdriveRating;
        @JsonProperty("MGLT")
        private String mglt;
        @JsonProperty("starship_class")
        private String starshipClass;
        private Set<String> pilots;
        private List<String> films;
        private Date created;
        private Date edited;
        private String url;
    }
}
