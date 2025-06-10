package ar.edu.unq.weather_loader_component.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WeatherReport {

    private Double temperature;
    private String cityName;
    private LocalDateTime timestamp;

    public WeatherReport(
            Double temperature,
            String cityName
    ) {
        this.temperature = temperature;
        this.cityName = cityName;
        this.timestamp = LocalDateTime.now();
    }
}
