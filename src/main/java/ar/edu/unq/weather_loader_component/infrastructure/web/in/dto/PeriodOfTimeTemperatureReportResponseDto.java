package ar.edu.unq.weather_loader_component.infrastructure.web.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class PeriodOfTimeTemperatureReportResponseDto {

    @JsonProperty("temperature_reports")
    private List<TemperatureReportResponseDto> temperatureReports;

    private LocalDateTime from;

    private LocalDateTime to;
}
