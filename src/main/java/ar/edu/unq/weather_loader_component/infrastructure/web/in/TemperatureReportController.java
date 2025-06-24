package ar.edu.unq.weather_loader_component.infrastructure.web.in;

import ar.edu.unq.weather_loader_component.domain.model.TemperatureReport;
import ar.edu.unq.weather_loader_component.domain.port.in.TemperatureReportUseCasePort;
import ar.edu.unq.weather_loader_component.infrastructure.web.in.dto.TemperatureReportResponseDto;
import ar.edu.unq.weather_loader_component.infrastructure.web.in.dto.PeriodOfTimeTemperatureReportResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/temperature/report")
public class TemperatureReportController {

    private final TemperatureReportUseCasePort temperatureReportUseCasePort;

    public TemperatureReportController(TemperatureReportUseCasePort temperatureReportUseCasePort) {
        this.temperatureReportUseCasePort = temperatureReportUseCasePort;
    }

    @GetMapping("/current")
    public ResponseEntity<TemperatureReportResponseDto> getCurrentTemperatureReport() {
        TemperatureReport temperatureReport = temperatureReportUseCasePort.getCurrentTemperatureReport();
        return ResponseEntity.ok(generateCurrentTemperatureReportResponseDtoFrom(temperatureReport));
    }

    @GetMapping("/filter-by")
    public ResponseEntity<PeriodOfTimeTemperatureReportResponseDto> getPeriodOfTimeTemperatureReports(@RequestParam(name = "from") LocalDateTime from,
                                                                                                      @RequestParam(name = "to") LocalDateTime to) {
        List<TemperatureReport> reports = temperatureReportUseCasePort.getPeriodOfTimeTemperatureReports(from, to);
        return ResponseEntity.ok(generatePeriodOfTimeTemperatureReportResponseDtoFrom(reports, from, to));
    }


    private TemperatureReportResponseDto generateCurrentTemperatureReportResponseDtoFrom(TemperatureReport temperatureReport) {
        return new TemperatureReportResponseDto(
                temperatureReport.getTemperature(),
                temperatureReport.getCityName(),
                temperatureReport.getTimestamp()
        );
    }

    private PeriodOfTimeTemperatureReportResponseDto generatePeriodOfTimeTemperatureReportResponseDtoFrom(List<TemperatureReport> reports, LocalDateTime from, LocalDateTime to) {
        List<TemperatureReportResponseDto> temperatureReports = reports.stream().map(report->new TemperatureReportResponseDto(
                report.getTemperature(),
                report.getCityName(),
                report.getTimestamp()
        )).toList();

        return new PeriodOfTimeTemperatureReportResponseDto(temperatureReports, from, to);
    }
}
