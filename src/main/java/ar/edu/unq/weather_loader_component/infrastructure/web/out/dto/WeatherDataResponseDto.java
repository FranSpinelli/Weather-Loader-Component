package ar.edu.unq.weather_loader_component.infrastructure.web.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherDataResponseDto {

    @JsonProperty("weather")
    private List<GeneralWeatherDataEmbeddedResponseDto> generalWeatherDataEmbeddedResponseDtos;

    @JsonProperty("main")
    private MainWeatherDataEmbeddedResponseDto mainWeatherDataEmbeddedResponseDto;

    @JsonProperty("visibility")
    private Integer visibilityMeters;

    @JsonProperty("wind")
    private WindDataEmbeddedResponseDto windDataEmbeddedResponseDto;

    @JsonProperty("clouds")
    private CloudsDataEmbeddedDto cloudsDataEmbeddedResponseDto;

    @JsonProperty("name")
    private String cityName;
}
