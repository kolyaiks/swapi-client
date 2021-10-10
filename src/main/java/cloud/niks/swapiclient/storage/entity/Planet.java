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
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Planet")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Planet {

    private String name;
    @Id
    @Column(name = "id", updatable = false, unique = true)
    private String url;

}
