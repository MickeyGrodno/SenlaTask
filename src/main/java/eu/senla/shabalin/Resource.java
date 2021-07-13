package eu.senla.shabalin;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource {
    private Integer id;
    private String name;
    private Integer year;
    private String color;
    @JsonProperty("pantone_value")
    private String pantoneValue;
}
