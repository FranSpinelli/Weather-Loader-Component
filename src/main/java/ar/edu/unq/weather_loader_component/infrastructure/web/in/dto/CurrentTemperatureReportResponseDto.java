package ar.edu.unq.weather_loader_component.infrastructure.web.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CurrentTemperatureReportResponseDto {

    private Double temperature;

    @JsonProperty("city_name")
    private String cityName;

    private LocalDateTime timestamp;
}
