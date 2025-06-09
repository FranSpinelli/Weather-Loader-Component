package ar.edu.unq.weather_loader_component.domain.port.in;

import ar.edu.unq.weather_loader_component.domain.model.PeriodOfTimeTemperatureReport;
import ar.edu.unq.weather_loader_component.domain.model.TemperatureReport;

import java.time.LocalDateTime;

public interface TemperatureReportUseCasePort {

    TemperatureReport getCurrentTemperatureReport();

    PeriodOfTimeTemperatureReport getPeriodOfTimeTemperatureReport(LocalDateTime startDate, LocalDateTime endDate);
}
