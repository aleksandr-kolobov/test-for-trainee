package main.service;

import main.model.Town;
import main.repository.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TownServiceImpl implements TownService {

    @Autowired
    private TownRepository teamRepository;

    @Override
    public Town findById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    @Override
    public Town findByName(String name) {
        return teamRepository.findByName(name).orElse(null);
    }

    @Override
    public boolean existsById(Integer id) {
        return teamRepository.existsById(id);
    }

    @Override
    public Town save(Town team) {
        return teamRepository.save(team);
    }

    @Override
    public Town update(Town team) {
        return teamRepository.save(team);
    }

    @Override
    public void deleteById(Integer id) {
       teamRepository.deleteById(id);
    }

    @Override
    public List<Town> findAll() {
        return teamRepository.findAll();
    }
}
