package ar.edu.unq.weather_loader_component.application;

import ar.edu.unq.weather_loader_component.application.exceptions.InformationNotAvailableException;
import ar.edu.unq.weather_loader_component.application.exceptions.RateLimiterException;
import ar.edu.unq.weather_loader_component.domain.model.TemperatureReport;
import ar.edu.unq.weather_loader_component.domain.model.WeatherReport;
import ar.edu.unq.weather_loader_component.domain.port.in.TemperatureReportUseCasePort;
import ar.edu.unq.weather_loader_component.domain.port.out.WeatherReportRepositoryPort;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureReportUseCaseAdapter implements TemperatureReportUseCasePort {

    private final WeatherReportRepositoryPort weatherReportRepositoryPort;

    public TemperatureReportUseCaseAdapter(WeatherReportRepositoryPort weatherReportRepositoryPort) {
        this.weatherReportRepositoryPort = weatherReportRepositoryPort;
    }

    @Override
    @RateLimiter(name = "get-temperature-limiter", fallbackMethod = "fallbackGetTemperatureReportError")
    public TemperatureReport getCurrentTemperatureReport() {
        WeatherReport currentWeatherReport = weatherReportRepositoryPort.getCurrentWeatherReport().orElseThrow(() -> new InformationNotAvailableException("Current Weather Report not found."));

        return new TemperatureReport(
                currentWeatherReport.getTemperature(),
                currentWeatherReport.getCityName(),
                currentWeatherReport.getTimestamp()
        );
    }

    public TemperatureReport fallbackGetTemperatureReportError(RequestNotPermitted ex) {
        throw new RateLimiterException("Too many requests for method getCurrentTemperatureReport.");
    }

    @Override
    @RateLimiter(name = "get-temperature-limiter", fallbackMethod = "fallbackGetPeriodReportError")
    public List<TemperatureReport> getPeriodOfTimeTemperatureReports(LocalDateTime startDate, LocalDateTime endDate) {
        return weatherReportRepositoryPort.getPeriodOfTimeWeatherReport(startDate, endDate)
                .stream()
                .map(weatherReport -> new TemperatureReport(weatherReport.getTemperature(),
                        weatherReport.getCityName(),
                        weatherReport.getTimestamp()
                ))
                .collect(Collectors.toList());

    }

    public List<TemperatureReport> fallbackGetPeriodReportError(LocalDateTime startDate, LocalDateTime endDate, RequestNotPermitted ex) {
        throw new RateLimiterException("Too many requests for method getPeriodOfTimeTemperatureReports.");
    }
}
