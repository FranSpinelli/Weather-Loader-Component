package ar.edu.unq.weather_loader_component.infrastructure.web.out.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TemperatureReportEmbeddedResponseDto {

    @JsonProperty("temp")
    private Double weatherTemperature;

}
