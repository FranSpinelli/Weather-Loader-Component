package ar.edu.unq.weather_loader_component.domain.port.out;

import ar.edu.unq.weather_loader_component.infrastructure.web.out.dto.WeatherDataResponseDto;

public interface WeatherDataRepositoryPort {
    WeatherDataResponseDto getCurrentWeatherData();
}
