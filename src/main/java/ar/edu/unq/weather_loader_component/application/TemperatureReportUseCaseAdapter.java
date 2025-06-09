package ar.edu.unq.weather_loader_component.application;

import ar.edu.unq.weather_loader_component.application.exceptions.InformationNotAvailableException;
import ar.edu.unq.weather_loader_component.domain.model.PeriodOfTimeTemperatureReport;
import ar.edu.unq.weather_loader_component.domain.model.TemperatureReport;
import ar.edu.unq.weather_loader_component.domain.model.WeatherReport;
import ar.edu.unq.weather_loader_component.domain.port.in.TemperatureReportUseCasePort;
import ar.edu.unq.weather_loader_component.domain.port.out.WeatherReportRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    public PeriodOfTimeTemperatureReport getPeriodOfTimeTemperatureReport(LocalDateTime startDate, LocalDateTime endDate) {
        List<WeatherReport> periodOfTimeWeatherReport = weatherReportRepositoryPort.getPeriodOfTimeWeatherReport(startDate, endDate);

        Double periodOfTimeAverageTemperature = periodOfTimeWeatherReport.stream().mapToDouble(WeatherReport::getTemperature).sum() / periodOfTimeWeatherReport.size();

        return new PeriodOfTimeTemperatureReport(
                periodOfTimeAverageTemperature,
                periodOfTimeWeatherReport.get(0).getCityName(),
                startDate,
                endDate
        );
    }
}
