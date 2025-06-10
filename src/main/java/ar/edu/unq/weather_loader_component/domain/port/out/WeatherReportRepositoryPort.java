package ar.edu.unq.weather_loader_component.domain.port.out;

import ar.edu.unq.weather_loader_component.domain.model.WeatherReport;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WeatherReportRepositoryPort {

    WeatherReport save(WeatherReport weatherReport);

    Optional<WeatherReport> getCurrentWeatherReport();

    List<WeatherReport> getPeriodOfTimeWeatherReport(LocalDateTime startDate, LocalDateTime endDate);
}
