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
        LocalDateTime currentLocalDateTime = LocalDateTime.now();
        LocalDateTime threeHoursAgoLocalDateTime = currentLocalDateTime.minusHours(3);

        List<WeatherReportDocument> weatherReportDocumentList = weatherReportMongoRepository.findByTimestampBetweenOrderByTimestampDesc(threeHoursAgoLocalDateTime, currentLocalDateTime);

        if(weatherReportDocumentList.isEmpty()) {
            return Optional.empty();
        }else{
            WeatherReportDocument weatherReportDocument = weatherReportDocumentList.get(1);
            return Optional.of(new WeatherReport(
                    weatherReportDocument.getTemperature(),
                    weatherReportDocument.getCityName(),
                    weatherReportDocument.getTimestamp()
            ));
        }
    }

    @Override
    public List<WeatherReport> getPeriodOfTimeWeatherReport(LocalDateTime startDate, LocalDateTime endDate) {
        List<WeatherReportDocument>  weatherReportDocumentsOfGivenPeriod = weatherReportMongoRepository.findByTimestampBetweenOrderByTimestampDesc(startDate, endDate);

        return weatherReportDocumentsOfGivenPeriod.stream().map(weatherReportDocument ->
                new WeatherReport(
                        weatherReportDocument.getTemperature(),
                        weatherReportDocument.getCityName(),
                        weatherReportDocument.getTimestamp()
                )
        ).toList();
    }
}
