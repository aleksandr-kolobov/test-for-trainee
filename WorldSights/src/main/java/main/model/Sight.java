package main.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Sight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate date;
    private String description;
    @Enumerated(EnumType.STRING)
    private SightType type;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Town town;

}
