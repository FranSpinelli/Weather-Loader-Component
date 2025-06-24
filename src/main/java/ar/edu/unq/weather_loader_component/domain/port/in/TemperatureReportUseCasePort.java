package ar.edu.unq.weather_loader_component.domain.port.in;

import ar.edu.unq.weather_loader_component.domain.model.TemperatureReport;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureReportUseCasePort {

    TemperatureReport getCurrentTemperatureReport();

    List<TemperatureReport> getPeriodOfTimeTemperatureReports(LocalDateTime startDate, LocalDateTime endDate);

}
