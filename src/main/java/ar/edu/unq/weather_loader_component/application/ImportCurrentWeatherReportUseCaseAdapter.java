package ar.edu.unq.weather_loader_component.application;

import ar.edu.unq.weather_loader_component.domain.model.WeatherReport;
import ar.edu.unq.weather_loader_component.domain.port.in.ImportCurrentWeatherReportUseCasePort;
import ar.edu.unq.weather_loader_component.domain.port.out.CurrentWeatherReportRepositoryPort;
import ar.edu.unq.weather_loader_component.domain.port.out.WeatherReportRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ImportCurrentWeatherReportUseCaseAdapter implements ImportCurrentWeatherReportUseCasePort {

    private final CurrentWeatherReportRepositoryPort currentWeatherReportRepositoryPort;
    private final WeatherReportRepositoryPort weatherReportRepositoryPort;

    public ImportCurrentWeatherReportUseCaseAdapter(
            CurrentWeatherReportRepositoryPort currentWeatherReportRepositoryPort,
            WeatherReportRepositoryPort weatherReportRepositoryPort
    ) {
        this.currentWeatherReportRepositoryPort = currentWeatherReportRepositoryPort;
        this.weatherReportRepositoryPort = weatherReportRepositoryPort;
    }

    @Override
    public void importCurrentWeatherReport() {
        WeatherReport weatherReportResponseDto = currentWeatherReportRepositoryPort.getCurrentWeatherReport();
        weatherReportRepositoryPort.save(weatherReportResponseDto);
    }
}
