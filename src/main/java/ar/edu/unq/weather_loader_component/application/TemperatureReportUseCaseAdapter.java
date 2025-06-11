package ar.edu.unq.weather_loader_component.application;

import ar.edu.unq.weather_loader_component.application.exceptions.InformationNotAvailableException;
import ar.edu.unq.weather_loader_component.domain.model.TemperatureReport;
import ar.edu.unq.weather_loader_component.domain.model.WeatherReport;
import ar.edu.unq.weather_loader_component.domain.port.in.TemperatureReportUseCasePort;
import ar.edu.unq.weather_loader_component.domain.port.out.WeatherReportRepositoryPort;
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
    public TemperatureReport getCurrentTemperatureReport() {
        WeatherReport currentWeatherReport = weatherReportRepositoryPort.getCurrentWeatherReport().orElseThrow(() -> new InformationNotAvailableException("Current Weather Report not found."));

        return new TemperatureReport(
                currentWeatherReport.getTemperature(),
                currentWeatherReport.getCityName(),
                currentWeatherReport.getTimestamp()
        );
    }

    @Override
    public List<TemperatureReport> getPeriodOfTimeTemperatureReport(LocalDateTime startDate, LocalDateTime endDate) {
        return weatherReportRepositoryPort.getPeriodOfTimeWeatherReport(startDate, endDate)
                .stream()
                .map(weatherReport -> new TemperatureReport(weatherReport.getTemperature(),
                        weatherReport.getCityName(),
                        weatherReport.getTimestamp()//.minusHours(3)
                ))
                .collect(Collectors.toList());
    }
}
