package main.controller;

import main.model.Sight;
import main.model.SightType;
import main.model.Town;
import main.model.dto.DtoSight;
import main.model.dto.SightMapper;
import main.service.SightService;
import main.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class SightController {

    @Autowired
    private SightService sightServiceImpl;

    @Autowired
    private TownService townServiceImpl;

    @GetMapping("/sights")
    public List<DtoSight> getSightsList(@RequestParam(defaultValue = "false") boolean sort
            , @RequestParam(defaultValue = "default") String type
            , @RequestParam(defaultValue = "-") String townName) {

        ArrayList<Sight> arrayList = new ArrayList<>();
        arrayList.addAll(sightServiceImpl.findAll());
        arrayList.sort(Comparator.comparing(Sight::getId));

        SightType sightType = SightType.valueOf(type.toUpperCase());

        if (sightType != SightType.DEFAULT) {
            Stream<Sight> stream = arrayList.stream().filter(s -> s.getType() == sightType);
            arrayList = new ArrayList<>();
            arrayList.addAll(stream.collect(Collectors.toList()));
        }

        String str = townName.substring(0, 1).toUpperCase() + townName.substring(1).toLowerCase();
        Town town = townServiceImpl.findByName(str);
        if (town != null) {
            Stream<Sight> stream = arrayList.stream().filter(s -> s.getTown().getName().equals(str));
            arrayList = new ArrayList<>();
            arrayList.addAll(stream.collect(Collectors.toList()));
        }
        if (sort) {
            arrayList.sort(Comparator.comparing(Sight::getName));
        }
        return arrayList.stream().map(SightMapper::map).collect(Collectors.toList());
    }


    @GetMapping("/sights/{id}")
    public ResponseEntity<DtoSight> getSight(@PathVariable int id) {
        Sight sight = sightServiceImpl.findById(id);

        return (sight == null) ? ResponseEntity.notFound().build() :
                new ResponseEntity(SightMapper.map(sight), HttpStatus.OK);
    }


    @PostMapping("/sights/")
    public ResponseEntity<DtoSight> addSight(DtoSight dtoSight) {

        Sight sight = new Sight();
        sight.setName(dtoSight.getName());
        sight.setDate(LocalDate.parse(dtoSight.getDate()));
        sight.setDescription(dtoSight.getDescription());
        sight.setType(SightType.valueOf(dtoSight.getType().toUpperCase()));
        String str = dtoSight.getTown();
        str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
        Town town = townServiceImpl.findByName(str);
        sight.setTown((town != null) ? town : townServiceImpl.findById(0));
        return new ResponseEntity(SightMapper.map(sightServiceImpl.save(sight)), HttpStatus.OK);
    }


    @PatchMapping("/sights/{id}")
    public ResponseEntity<DtoSight> correctSight(@PathVariable int id, String description) {
        Sight  sight = sightServiceImpl.findById(id);
        if ((sight != null) && (!description.isEmpty())) {
            sight.setDescription(description);
            return new ResponseEntity(SightMapper.map(sightServiceImpl.save(sight)), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/sights/{id}")
    public ResponseEntity deleteSight(@PathVariable int id) {
        if (sightServiceImpl.existsById(id)) {
            sightServiceImpl.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}