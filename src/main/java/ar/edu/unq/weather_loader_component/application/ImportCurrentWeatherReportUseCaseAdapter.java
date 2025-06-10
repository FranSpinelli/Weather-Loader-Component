package ar.edu.unq.weather_loader_component.application;

import ar.edu.unq.weather_loader_component.domain.model.WeatherReport;
import ar.edu.unq.weather_loader_component.domain.port.in.ImportCurrentWeatherReportUseCasePort;
import ar.edu.unq.weather_loader_component.domain.port.out.CurrentWeatherReportRepositoryPort;
import ar.edu.unq.weather_loader_component.domain.port.out.WeatherReportRepositoryPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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

        log.info("Getting Current Weather Report from OpenWeatherApi.");
        WeatherReport weatherReportResponseDto = currentWeatherReportRepositoryPort.getCurrentWeatherReport();

        log.info("Saving Current Weather Report to Database.");
        weatherReportRepositoryPort.save(weatherReportResponseDto);
    }
}
