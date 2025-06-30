package ar.edu.unq.weather_loader_component.domain.port.out;

import ar.edu.unq.weather_loader_component.domain.model.WeatherReport;

import java.util.Optional;

public interface CurrentWeatherReportRepositoryPort {

    Optional<WeatherReport> getCurrentWeatherReport();
}
