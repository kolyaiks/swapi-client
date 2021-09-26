package cloud.niks.swapiclient.repository;

import cloud.niks.swapiclient.entity.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface StarshipRepository extends JpaRepository<Starship, String> {

    @Query(nativeQuery = true,
            value = "select  s.id starship_id, s.name starship_name, s.cargo_capacity, sc.character_id, c.name character_name\n" +
                    "from Starship s\n" +
                    "join Starships_Characters sc on sc.starship_id=s.id\n" +
                    "join Character c on c.id=sc.character_id\n" +
                    "order by s.cargo_capacity desc\n" +
                    "limit 10")
    List<Tuple> getDataByCustomQuery();

}
