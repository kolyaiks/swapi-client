package cloud.niks.swapiclient.storage.repository;

import cloud.niks.swapiclient.storage.entity.Planet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetRepository extends JpaRepository<Planet, String> {
}
