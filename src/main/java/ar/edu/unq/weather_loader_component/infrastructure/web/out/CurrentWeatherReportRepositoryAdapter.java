package ar.edu.unq.weather_loader_component.infrastructure.web.out;

import ar.edu.unq.weather_loader_component.domain.model.*;
import ar.edu.unq.weather_loader_component.domain.port.out.CurrentWeatherReportRepositoryPort;
import ar.edu.unq.weather_loader_component.infrastructure.web.out.dto.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class CurrentWeatherReportRepositoryAdapter implements CurrentWeatherReportRepositoryPort {

    private static final String WEATHER_MAP_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String LATITUDE_QUERY_PARAM = "lat";
    private static final String LATITUDE_QUERY_PARAM_VALUE = "-34.7303025";
    private static final String LONGITUDE_QUERY_PARAM = "lon";
    private static final String LONGITUDE_QUERY_PARAM_VALUE = "-58.268868";
    private static final String LANGUAGE_QUERY_PARAM = "lang";
    private static final String LANGUAGE_QUERY_PARAM_VALUE = "sp";
    private static final String UNITS_QUERY_PARAM = "units";
    private static final String UNITS_QUERY_PARAM_VALUE = "metric";
    private static final String APP_ID_QUERY_PARAM = "appid";

    private final RestClient restClient;

    public CurrentWeatherReportRepositoryAdapter(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public WeatherReport getCurrentWeatherReport() {
        URI weatherMapUri = UriComponentsBuilder.fromHttpUrl(WEATHER_MAP_URL)
                .queryParam(LATITUDE_QUERY_PARAM, LATITUDE_QUERY_PARAM_VALUE)
                .queryParam(LONGITUDE_QUERY_PARAM, LONGITUDE_QUERY_PARAM_VALUE)
                .queryParam(LANGUAGE_QUERY_PARAM, LANGUAGE_QUERY_PARAM_VALUE)
                .queryParam(UNITS_QUERY_PARAM, UNITS_QUERY_PARAM_VALUE)
                .queryParam(APP_ID_QUERY_PARAM, "${ar.edu.unq.weather.loader.component.weather.map.api.key}")
                .build().toUri();

        CurrentWeatherReportResponseDto currentWeatherReportResponseDto = restClient.get().uri(weatherMapUri).retrieve().body(CurrentWeatherReportResponseDto.class);

        return generateWeatherReportFrom(currentWeatherReportResponseDto);
    }

    private WeatherReport generateWeatherReportFrom(CurrentWeatherReportResponseDto currentWeatherReportResponseDto) {
        return new WeatherReport(
                currentWeatherReportResponseDto.getTemperatureReportEmbeddedResponseDto().getWeatherTemperature(),
                currentWeatherReportResponseDto.getCityName()
        );
    }
}
