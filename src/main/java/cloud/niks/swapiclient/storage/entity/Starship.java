package cloud.niks.swapiclient.storage.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity(name = "Starship")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Starship {

    private String name;
    @Column(name = "cargo_capacity", nullable = false)
    private Long cargoCapacity;
    private String model;
    private String manufacturer;
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private String url;
    @ManyToMany
    @JoinTable(
            name = "Starships_Characters",
            joinColumns = @JoinColumn(name = "starship_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private Set<Character> pilots;

}
