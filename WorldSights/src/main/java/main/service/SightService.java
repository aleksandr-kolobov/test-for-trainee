package main.service;

import main.model.Sight;
import main.model.Town;

import java.util.List;

public interface SightService {
    Sight findById(Integer id);

    Sight findByName(String name);

    boolean existsById(Integer id);

    Sight save(Sight player);

    Sight update(Sight player);

    void deleteById(Integer id);

    List<Sight> findAll();
}
