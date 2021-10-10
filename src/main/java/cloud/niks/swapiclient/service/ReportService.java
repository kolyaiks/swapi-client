package cloud.niks.swapiclient.service;

import cloud.niks.swapiclient.dto.ReportRow;
import cloud.niks.swapiclient.storage.repository.StarshipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final StarshipRepository starshipRepository;

    public List<ReportRow> getReport() {

        List<Tuple> dataByCustomQuery = starshipRepository.getDataByCustomQuery();

        return dataByCustomQuery.stream()
                .map(element -> ReportRow.builder()
                        // Getting column names from StarshipRepository SQL query:
                        // select  s.id starship_id, s.name starship_name,  s.cargo_capacity,
                        // s.model, s.manufacturer, sc.character_id, c.name character_name,
                        // c.gender character_gender, p.name character_homeworld
                        .starshipId(element.get("starship_id").toString())
                        .starshipName(element.get("starship_name").toString())
                        .cargoCapacity(Long.parseLong(element.get("cargo_capacity").toString()))
                        .model(element.get("model").toString())
                        .manufacturer(element.get("manufacturer").toString())
                        .characterId(element.get("character_id").toString())
                        .characterName(element.get("character_name").toString())
                        .characterGender(element.get("character_gender").toString())
                        .characterHomeworld(element.get("character_homeworld").toString())
                        .build()
                ).collect(Collectors.toList());
    }

}
