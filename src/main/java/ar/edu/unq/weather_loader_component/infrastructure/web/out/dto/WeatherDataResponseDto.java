package ar.edu.unq.weather_loader_component.infrastructure.web.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherDataResponseDto {

    @JsonProperty("weather")
    private List<GeneralWeatherDataEmbeddedResponseDto> generalData;

    @JsonProperty("main")
    private MainWeatherDataEmbeddedResponseDto mainWeatherData;

    @JsonProperty("visibility")
    private String visibilityDistanceMeters;

    @JsonProperty("wind")
    private WindDataEmbeddedResponseDto windData;

    @JsonProperty("clouds")
    private CloudsDataEmbeddedDto cloudsData;

    @JsonProperty("name")
    private String cityName;
}
