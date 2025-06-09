package ar.edu.unq.weather_loader_component.infrastructure.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherReportMongoRepository extends MongoRepository<WeatherReportDocument, String> {
}
