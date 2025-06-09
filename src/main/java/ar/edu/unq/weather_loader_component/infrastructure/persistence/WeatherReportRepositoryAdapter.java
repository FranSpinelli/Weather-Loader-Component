package ar.edu.unq.weather_loader_component.infrastructure.persistence;

import ar.edu.unq.weather_loader_component.domain.model.WeatherReport;
import ar.edu.unq.weather_loader_component.domain.port.out.WeatherReportRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WeatherReportRepositoryAdapter implements WeatherReportRepositoryPort {

    private final WeatherReportMongoRepository weatherReportMongoRepository;

    public WeatherReportRepositoryAdapter(WeatherReportMongoRepository weatherReportMongoRepository) {
        this.weatherReportMongoRepository = weatherReportMongoRepository;
    }

    @Override
    public WeatherReport save(WeatherReport weatherReport) {
        WeatherReportDocument weatherReportDocument = new WeatherReportDocument(
                UUID.randomUUID().toString(),
                weatherReport.getTemperature(),
                weatherReport.getCityName(),
                weatherReport.getTimestamp()
        );

        weatherReportMongoRepository.save(weatherReportDocument);

        return weatherReport;
    }
}
