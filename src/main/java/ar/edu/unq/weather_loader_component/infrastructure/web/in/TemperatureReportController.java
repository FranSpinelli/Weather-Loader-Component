package ar.edu.unq.weather_loader_component.infrastructure.web.in;

import ar.edu.unq.weather_loader_component.domain.model.TemperatureReport;
import ar.edu.unq.weather_loader_component.domain.port.in.TemperatureReportUseCasePort;
import ar.edu.unq.weather_loader_component.infrastructure.web.in.dto.CurrentTemperatureReportResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/temperature/report")
public class TemperatureReportController {

    private final TemperatureReportUseCasePort temperatureReportUseCasePort;

    public TemperatureReportController(TemperatureReportUseCasePort temperatureReportUseCasePort) {
        this.temperatureReportUseCasePort = temperatureReportUseCasePort;
    }

    @GetMapping("/current")
    public ResponseEntity<CurrentTemperatureReportResponseDto> getCurrentWeatherReport() {
        TemperatureReport temperatureReport = temperatureReportUseCasePort.getCurrentTemperatureReport();
        return ResponseEntity.ok(generateCurrentTemperatureReportResponseDtoFrom(temperatureReport));
    }

    @GetMapping("/filter-by")
    public ResponseEntity<List<CurrentTemperatureReportResponseDto>> getLastDayWeatherReport(@RequestParam(name = "from") LocalDateTime from,
                                                                                                  @RequestParam(name = "to") LocalDateTime to) {
        var report = temperatureReportUseCasePort.getPeriodOfTimeTemperatureReport(from, to);
        return ResponseEntity.ok(generatePeriodOfTimeTemperatureReportResponseDtoFrom(report));
    }


    private CurrentTemperatureReportResponseDto generateCurrentTemperatureReportResponseDtoFrom(TemperatureReport temperatureReport) {
        return new CurrentTemperatureReportResponseDto(
                temperatureReport.getTemperature(),
                temperatureReport.getCityName(),
                temperatureReport.getTimestamp()
        );
    }

    private List<CurrentTemperatureReportResponseDto> generatePeriodOfTimeTemperatureReportResponseDtoFrom(List<TemperatureReport> reports) {
        return reports.stream().map(report->new CurrentTemperatureReportResponseDto(
                report.getTemperature(),
                report.getCityName(),
                report.getTimestamp()
        )).collect(Collectors.toList());
    }
}
