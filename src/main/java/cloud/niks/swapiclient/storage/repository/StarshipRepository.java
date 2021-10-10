package cloud.niks.swapiclient.storage.repository;

import cloud.niks.swapiclient.storage.entity.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Tuple;
import java.util.List;

public interface StarshipRepository extends JpaRepository<Starship, String> {

    @Query(nativeQuery = true,
            value = "select  s.id starship_id, s.name starship_name,  s.cargo_capacity, s.model, s.manufacturer, sc.character_id, c.name character_name, c.gender character_gender, p.name character_homeworld\n" +
                    "from Starship s\n" +
                    "join Starships_Characters sc on sc.starship_id=s.id\n" +
                    "join Character c on c.id=sc.character_id\n" +
                    "join Planet p on p.id=c.planet_id\n" +
                    "order by s.cargo_capacity desc\n" +
                    "limit 10")
    List<Tuple> getDataByCustomQuery();

}
