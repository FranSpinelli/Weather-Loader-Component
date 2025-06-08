package ar.edu.unq.weather_loader_component.domain.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MainWeatherData {

    private Double temperature;
    private Double feelsLikeTemperature;
    private Double minTemperatureInCelsius;
    private Double maxTemperatureInCelsius;
    private Integer pressure;
    private Integer humidity;
}
