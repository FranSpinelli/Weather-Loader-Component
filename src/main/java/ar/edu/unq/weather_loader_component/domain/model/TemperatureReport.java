package ar.edu.unq.weather_loader_component.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TemperatureReport {

    private Double temperature;
    private String cityName;
    private LocalDateTime timestamp;
}
