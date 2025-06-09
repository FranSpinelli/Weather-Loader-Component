package ar.edu.unq.weather_loader_component.infrastructure.web.in;

import ar.edu.unq.weather_loader_component.domain.model.PeriodOfTimeTemperatureReport;
import ar.edu.unq.weather_loader_component.domain.model.TemperatureReport;
import ar.edu.unq.weather_loader_component.domain.port.in.TemperatureReportUseCasePort;
import ar.edu.unq.weather_loader_component.infrastructure.web.in.dto.CurrentTemperatureReportResponseDto;
import ar.edu.unq.weather_loader_component.infrastructure.web.in.dto.PeriodOfTimeTemperatureReportResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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

    @GetMapping("/last-day")
    public ResponseEntity<PeriodOfTimeTemperatureReportResponseDto> getLastDayWeatherReport() {
        LocalDateTime now = LocalDateTime.now().plusHours(3);
        LocalDateTime oneWeekAgo = now.minusWeeks(1).plusHours(3);

        PeriodOfTimeTemperatureReport periodOfTimeTemperatureReport = temperatureReportUseCasePort.getPeriodOfTimeTemperatureReport(oneWeekAgo, now);
        return ResponseEntity.ok(generatePeriodOfTimeTemperatureReportResponseDtoFrom(periodOfTimeTemperatureReport));
    }

    @GetMapping("/last-week")
    public ResponseEntity<PeriodOfTimeTemperatureReportResponseDto> getLastWeekWeatherReport() {
        LocalDateTime now = LocalDateTime.now().plusHours(3);
        LocalDateTime oneDayAgo = now.minusDays(1).plusHours(3);

        PeriodOfTimeTemperatureReport temperatureReport = temperatureReportUseCasePort.getPeriodOfTimeTemperatureReport(oneDayAgo, now);
        return ResponseEntity.ok(generatePeriodOfTimeTemperatureReportResponseDtoFrom(temperatureReport));
    }

    private CurrentTemperatureReportResponseDto generateCurrentTemperatureReportResponseDtoFrom(TemperatureReport temperatureReport) {
        return new CurrentTemperatureReportResponseDto(
                temperatureReport.getTemperature(),
                temperatureReport.getCityName(),
                temperatureReport.getTimestamp()
        );
    }

    private PeriodOfTimeTemperatureReportResponseDto generatePeriodOfTimeTemperatureReportResponseDtoFrom(PeriodOfTimeTemperatureReport periodOfTimeTemperatureReport) {
        return new PeriodOfTimeTemperatureReportResponseDto(
                periodOfTimeTemperatureReport.getTemperature(),
                periodOfTimeTemperatureReport.getCityName(),
                periodOfTimeTemperatureReport.getPeriodStart(),
                periodOfTimeTemperatureReport.getPeriodEnd()
        );
    }
}
