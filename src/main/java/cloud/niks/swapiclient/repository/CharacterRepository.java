package cloud.niks.swapiclient.repository;

import cloud.niks.swapiclient.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, String> {
}
