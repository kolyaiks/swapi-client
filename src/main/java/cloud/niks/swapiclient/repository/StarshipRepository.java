package cloud.niks.swapiclient.repository;

import cloud.niks.swapiclient.entity.Starship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarshipRepository extends JpaRepository<Starship, String> {
}
