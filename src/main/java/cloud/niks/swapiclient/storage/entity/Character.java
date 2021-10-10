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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Character")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Character {

    private String name;
    private String gender;
    @Id
    @Column(name = "id", updatable = false, unique = true)
    private String url;
    @ManyToMany(mappedBy = "pilots")
    private Set<Starship> starships = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "planet_id", referencedColumnName = "id")
    private Planet homeworld;

}
