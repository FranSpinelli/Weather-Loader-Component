package ar.edu.unq.weather_loader_component.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PeriodOfTimeTemperatureReport {

    private Double temperature;

    @JsonProperty("city_name")
    private String cityName;

    @JsonProperty("period_start")
    private LocalDateTime periodStart;

    @JsonProperty("period_start")
    private LocalDateTime periodEnd;
}
