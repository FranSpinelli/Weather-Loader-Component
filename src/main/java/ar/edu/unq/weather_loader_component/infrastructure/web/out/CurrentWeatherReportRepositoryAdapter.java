package ar.edu.unq.weather_loader_component.infrastructure.web.out;

import ar.edu.unq.weather_loader_component.domain.model.*;
import ar.edu.unq.weather_loader_component.domain.port.out.CurrentWeatherReportRepositoryPort;
import ar.edu.unq.weather_loader_component.infrastructure.web.out.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
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
    private final String appIdValue;

    public CurrentWeatherReportRepositoryAdapter(
            RestClient restClient,
            @Value("${ar.edu.unq.weather.loader.component.weather.map.api.key}") String appIdValue
    ) {
        this.restClient = restClient;
        this.appIdValue = appIdValue;
    }

    @Override
    public WeatherReport getCurrentWeatherReport() {
        URI openWeatherApiUri = UriComponentsBuilder.fromHttpUrl(WEATHER_MAP_URL)
                .queryParam(LATITUDE_QUERY_PARAM, LATITUDE_QUERY_PARAM_VALUE)
                .queryParam(LONGITUDE_QUERY_PARAM, LONGITUDE_QUERY_PARAM_VALUE)
                .queryParam(LANGUAGE_QUERY_PARAM, LANGUAGE_QUERY_PARAM_VALUE)
                .queryParam(UNITS_QUERY_PARAM, UNITS_QUERY_PARAM_VALUE)
                .queryParam(APP_ID_QUERY_PARAM, appIdValue)
                .build().toUri();

        log.info("OpenWeatherApi Uri: {}.", openWeatherApiUri);

        CurrentWeatherReportResponseDto currentWeatherReportResponseDto = restClient.get().uri(openWeatherApiUri).retrieve().body(CurrentWeatherReportResponseDto.class);
        log.info("Current Weather Report obtained from OpenWeatherApi: {}.", currentWeatherReportResponseDto);

        return generateWeatherReportFrom(currentWeatherReportResponseDto);
    }

    private WeatherReport generateWeatherReportFrom(CurrentWeatherReportResponseDto currentWeatherReportResponseDto) {
        return new WeatherReport(
                currentWeatherReportResponseDto.getTemperatureReportEmbeddedResponseDto().getWeatherTemperature(),
                currentWeatherReportResponseDto.getCityName()
        );
    }
}
