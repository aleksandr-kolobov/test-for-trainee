package main.model.dto;
import main.model.Sight;
import main.model.SightType;
import main.model.Town;
import main.model.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class SightMapper {
    @Autowired
    private static TownRepository townRepository;

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

    public static Sight remap(DtoSight dtoSight){
        Sight sight = new Sight();
        sight.setName(dtoSight.getName());
        sight.setDate(LocalDate.parse(dtoSight.getDate()));
        sight.setDescription(dtoSight.getDescription());
        sight.setType(SightType.valueOf(dtoSight.getType()));
        String str = dtoSight.getName();
        str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        Optional<Town> optional = townRepository.findByName(str);
        sight.setTown(optional.isPresent() ? optional.get() : townRepository.findById(0).get());
        return sight;
    }
}
