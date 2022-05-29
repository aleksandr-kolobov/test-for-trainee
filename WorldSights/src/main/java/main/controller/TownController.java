package main.controller;

import main.model.Town;
import main.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
public class TownController {
    @Autowired
    private TownRepository townRepository;

    @GetMapping("/towns")
    public List<Town> getTownsList(@RequestParam(defaultValue = "false") boolean sort){
       ArrayList<Town> arrayList = new ArrayList<>();
        arrayList.addAll(townRepository.findAll());
        arrayList.sort(Comparator.comparing(Town::getId));
        if (sort) {
            arrayList.sort(Comparator.comparing(Town::getName));
        }
        return arrayList;
    }

    @GetMapping("/towns/{nameOrId}")
    public ResponseEntity<Town> getTown(@PathVariable String nameOrId) {
        nameOrId = nameOrId.substring(0, 1).toUpperCase() + nameOrId.substring(1).toLowerCase();
        Optional<Town> optional = townRepository.findByName(nameOrId);
        if (optional.isPresent()) {
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        }
        optional = townRepository.findById(Integer.parseInt(nameOrId));
        return !optional.isPresent() ? ResponseEntity.notFound().build() :
                new ResponseEntity(optional.get(), HttpStatus.OK);
    }

    @PostMapping("/towns")
    public ResponseEntity<Town> addTown(Town town) {
        String str = town.getName();
        town.setName(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
        str = town.getCountry();
        town.setCountry(str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase());
        return new ResponseEntity(townRepository.save(town), HttpStatus.OK);
    }

//еще такой способ возвращать JSON ответ {"result":true}
/*    @PostMapping("/towns/")
    public HashMap<String, Boolean> addTown(Town town) {
        townRepository.save(town);
        return Map.of("result", true);
    }
//или такой способ возвращать ID {"id":5}
    @PostMapping("/towns/")
    public HashMap<String, Integer> addTown(Town town) {
        return Map.of("result", townRepository.save(town).getId());
    }*/

    @PatchMapping("/towns")
    public ResponseEntity<Town> correctTownByName(String name, boolean subway, int population) {
        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        Optional<Town> optional = townRepository.findByName(name);
        if(optional.isPresent()) {
            Town town = optional.get();
            town.setPopulation(population == 0 ? town.getPopulation() : population);
            town.setSubway(subway);
            return new ResponseEntity(townRepository.save(town), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/towns/{id}")
    public ResponseEntity<Town> correctTownById(@PathVariable int id, int population, boolean subway) {
        Optional<Town> optional = townRepository.findById(id);
        if(optional.isPresent()) {
            Town town = optional.get();
            town.setPopulation(population == 0 ? town.getPopulation() : population);
            town.setSubway(subway);
            return new ResponseEntity(townRepository.save(town), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

}
