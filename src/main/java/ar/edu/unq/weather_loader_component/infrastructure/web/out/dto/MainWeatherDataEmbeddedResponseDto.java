package ar.edu.unq.weather_loader_component.infrastructure.web.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MainWeatherDataEmbeddedResponseDto {

    @JsonProperty("temp")
    private Double temperature;

    @JsonProperty("feels_like")
    private Double feelsLikeTemperature;

    @JsonProperty("temp_min")
    private Double minTemperatureInCelsius;

    @JsonProperty("temp_max")
    private Double maxTemperatureInCelsius;

    private Integer pressure;

    private Integer humidity;
}
