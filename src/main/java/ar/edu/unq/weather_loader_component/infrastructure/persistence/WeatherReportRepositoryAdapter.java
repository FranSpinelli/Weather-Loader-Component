package ar.edu.unq.weather_loader_component.infrastructure.persistence;

import ar.edu.unq.weather_loader_component.domain.model.WeatherReport;
import ar.edu.unq.weather_loader_component.domain.port.out.WeatherReportRepositoryPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<WeatherReport> getCurrentWeatherReport() {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);

        Optional<WeatherReportDocument> weatherReportDocumentOptional = weatherReportMongoRepository.findTopByTimestampBetweenOrderByTimestampDesc(startOfDay, endOfDay);

        if(weatherReportDocumentOptional.isPresent()) {
            WeatherReportDocument weatherReportDocument = weatherReportDocumentOptional.get();
            return Optional.of(new WeatherReport(
                    weatherReportDocument.getTemperature(),
                    weatherReportDocument.getCityName(),
                    weatherReportDocument.getTimestamp().minusHours(3)
            ));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public List<WeatherReport> getPeriodOfTimeWeatherReport(LocalDateTime startDate, LocalDateTime endDate) {
        List<WeatherReportDocument>  weatherReportDocumentsOfGivenPeriod = weatherReportMongoRepository.findByTimestampBetween(startDate, endDate);

        return weatherReportDocumentsOfGivenPeriod.stream().map(weatherReportDocument ->
                new WeatherReport(
                        weatherReportDocument.getTemperature(),
                        weatherReportDocument.getCityName(),
                        weatherReportDocument.getTimestamp().minusHours(3)
                )
        ).toList();
    }
}
