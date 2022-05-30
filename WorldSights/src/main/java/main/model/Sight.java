package main.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sights")
@Getter
@Setter
public class Sight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate date;
    private String description;
    @Enumerated(EnumType.STRING)
    private SightType type;
    @ManyToOne(fetch = FetchType.LAZY)
    private Town town;
}
