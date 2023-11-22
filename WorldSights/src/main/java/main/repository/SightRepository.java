package main.repository;

import main.model.Sight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SightRepository  extends JpaRepository<Sight, Integer> {
    Optional<Sight> findByName(String name);
}