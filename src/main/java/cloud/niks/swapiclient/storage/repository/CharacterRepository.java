package cloud.niks.swapiclient.storage.repository;

import cloud.niks.swapiclient.storage.entity.Character;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CharacterRepository extends JpaRepository<Character, String> {

}
