package ar.edu.unq.weather_loader_component.domain.port.out;

import ar.edu.unq.weather_loader_component.domain.model.WeatherData;

public interface WeatherDataRepositoryPort {
    WeatherData getCurrentWeatherData();
}
