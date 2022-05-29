package main.controller;

import main.model.Sight;
import main.model.SightType;
import main.model.Town;
import main.model.dto.DtoSight;
import main.model.dto.SightMapper;
import main.repository.SightRepository;
import main.repository.TownRepository;
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
    private SightRepository sightRepository;
    @Autowired
    private TownRepository townRepository;

    @GetMapping("/sights")
    public List<DtoSight> getSightsList(@RequestParam(defaultValue = "false") boolean sort
            , @RequestParam(defaultValue = "default") String type
            , @RequestParam(defaultValue = "-") String town){
        ArrayList<Sight> arrayList = new ArrayList<>();
        arrayList.addAll(sightRepository.findAll());
        arrayList.sort(Comparator.comparing(Sight::getId));

        SightType sightType = SightType.valueOf(type.toUpperCase());
        if (sightType != SightType.DEFAULT) {
            Stream<Sight> stream = arrayList.stream().filter(s -> s.getType() == sightType);
            arrayList = new ArrayList<>();
            arrayList.addAll(stream.collect(Collectors.toList()));
        }

        String str = town.substring(0, 1).toUpperCase() + town.substring(1).toLowerCase();
        Optional<Town> optional = townRepository.findByName(str);
        if (optional.isPresent()) {
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
        Optional<Sight> optional = sightRepository.findById(id);
        return !optional.isPresent() ? ResponseEntity.notFound().build() :
                new ResponseEntity(SightMapper.map(optional.get()), HttpStatus.OK);
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
        Optional<Town> optional = townRepository.findByName(str);
        sight.setTown(optional.isPresent() ? optional.get() : townRepository.findById(0).get());
        return new ResponseEntity(SightMapper.map(sightRepository.save(sight)), HttpStatus.OK);
    }

    @PatchMapping("/sights/{id}")
    public ResponseEntity<DtoSight> correctSight(@PathVariable int id, String description) {
        Optional<Sight> optional = sightRepository.findById(id);
        if (optional.isPresent() && !description.isEmpty()) {
            Sight sight = optional.get();
            sight.setDescription(description);
            return new ResponseEntity(SightMapper.map(sightRepository.save(sight)), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/sights/{id}")
    public ResponseEntity deleteSight(@PathVariable int id) {
        if (sightRepository.existsById(id)) {
            sightRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}
