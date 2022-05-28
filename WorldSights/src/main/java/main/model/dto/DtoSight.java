package main.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoSight {
    private int id;
    private String name;
    private String date;
    private String description;
    private String type;
    private String town;
}
