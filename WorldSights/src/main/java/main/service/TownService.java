package main.service;

import main.model.Town;

import java.util.List;

public interface TownService {
    Town findById(Integer id);

    Town findByName(String name);

    boolean existsById(Integer id);

    Town save(Town team);

    Town update(Town team);

    void deleteById(Integer id);

    List<Town> findAll();
}
