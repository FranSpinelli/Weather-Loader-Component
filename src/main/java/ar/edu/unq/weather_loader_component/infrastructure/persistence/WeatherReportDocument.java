package ar.edu.unq.weather_loader_component.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Document("weather_reports")
public class WeatherReportDocument {

    private String id;

    private Double temperature;

    @Field("city_name")
    private String cityName;

    private LocalDateTime timestamp;
}
