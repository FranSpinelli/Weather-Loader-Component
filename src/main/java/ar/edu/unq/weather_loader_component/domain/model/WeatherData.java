package ar.edu.unq.weather_loader_component.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WeatherData {

    private List<GeneralWeatherData> generalWeatherData;
    private MainWeatherData mainWeatherData;
    private Integer visibilityMeters;
    private WindData windData;
    private CloudsData cloudsData;
    private String cityName;
    private LocalDateTime timestamp;

    public WeatherData(
            List<GeneralWeatherData> generalWeatherData,
            MainWeatherData mainWeatherData,
            Integer visibilityMeters,
            WindData windData,
            CloudsData cloudsData,
            String cityName
    ) {
        this.generalWeatherData = generalWeatherData;
        this.mainWeatherData = mainWeatherData;
        this.visibilityMeters = visibilityMeters;
        this.windData = windData;
        this.cloudsData = cloudsData;
        this.cityName = cityName;
        this.timestamp = LocalDateTime.now();
    }
}
