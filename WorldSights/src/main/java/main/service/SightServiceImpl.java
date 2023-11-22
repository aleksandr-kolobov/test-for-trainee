package main.service;

import main.model.Sight;
import main.repository.SightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SightServiceImpl implements SightService {

    @Autowired
    private SightRepository playerRepository;

    @Override
    public Sight findById(Integer id) {
        return playerRepository.findById(id).orElse(null);
    }

    @Override
    public Sight findByName(String name) {
        return playerRepository.findByName(name).orElse(null);
    }

    @Override
    public boolean existsById(Integer id) {
        return playerRepository.existsById(id);
    }

    @Override
    public Sight save(Sight player) {
        return playerRepository.save(player);
    }

    @Override
    public Sight update(Sight player) {
        return playerRepository.save(player);
    }

    @Override
    public void deleteById(Integer id) {
        playerRepository.deleteById(id);
    }

    @Override
    public List<Sight> findAll() {
        return playerRepository.findAll();
    }
}
