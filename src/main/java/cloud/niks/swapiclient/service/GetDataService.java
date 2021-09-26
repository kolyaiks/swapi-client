package cloud.niks.swapiclient.service;

import cloud.niks.swapiclient.dto.ResultRow;
import cloud.niks.swapiclient.repository.StarshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetDataService {

    private final StarshipRepository starshipRepository;

    public List<ResultRow> getData() {

        List<Tuple> dataByCustomQuery = starshipRepository.getDataByCustomQuery();

        return dataByCustomQuery.stream()
                .map(element -> ResultRow.builder()
                        // Getting column names from StarshipRepository SQL query:
                        // s.id starship_id, s.name starship_name, s.cargo_capacity, sc.character_id, c.name character_name
                        .starshipId(element.get("starship_id").toString())
                        .starshipName(element.get("starship_name").toString())
                        .cargoCapacity(Long.parseLong(element.get("cargo_capacity").toString()))
                        .characterId(element.get("character_id").toString())
                        .characterName(element.get("character_name").toString())
                        .build()
                ).collect(Collectors.toList());
    }

}
