package ar.edu.unq.weather_loader_component.infrastructure.web.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WindDataEmbeddedResponseDto {

    @JsonProperty("speed")
    private Double windSpeed;

    @JsonProperty("deg")
    private Double directionDegrees;

    @JsonProperty("gust")
    private Double windGustsSpeed;
}
