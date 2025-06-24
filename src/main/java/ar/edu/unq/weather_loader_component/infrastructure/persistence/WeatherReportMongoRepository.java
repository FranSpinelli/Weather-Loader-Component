package ar.edu.unq.weather_loader_component.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherReportMongoRepository extends MongoRepository<WeatherReportDocument, String> {

    List<WeatherReportDocument> findByTimestampBetweenOrderByTimestampDesc(LocalDateTime startTimestamp, LocalDateTime endTimestamp);
}
