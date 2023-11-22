package main.model.dto;

import main.model.Sight;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;

@Component
public class SightMapper {
    public static DtoSight map(Sight sight){

        DtoSight dtoSight = new DtoSight();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dtoSight.setId(sight.getId());
        dtoSight.setName(sight.getName());
        dtoSight.setDate(sight.getDate().format(f));
        dtoSight.setDescription(sight.getDescription());
        dtoSight.setType(sight.getType().name());
        dtoSight.setTown(sight.getTown().getName());
        return dtoSight;
    }
}
