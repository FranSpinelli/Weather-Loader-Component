package ar.edu.unq.weather_loader_component.infrastructure.web.out;

import ar.edu.unq.weather_loader_component.domain.model.*;
import ar.edu.unq.weather_loader_component.domain.port.out.CurrentWeatherReportRepositoryPort;
import ar.edu.unq.weather_loader_component.infrastructure.web.out.dto.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Slf4j
@Component
public class CurrentWeatherReportRepositoryAdapter implements CurrentWeatherReportRepositoryPort {

    //private static final String WEATHER_MAP_URL = "https://api.openweathermap.org/data/2.5/weather";
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
    private final String appUrl;

    public CurrentWeatherReportRepositoryAdapter(
            RestClient restClient,
            @Value("${ar.edu.unq.weather.loader.component.weather.map.api.key}") String appIdValue,
            @Value("${ar.edu.unq.weather.loader.component.weather.map.api.url}") String appUrl
    ) {
        this.restClient = restClient;
        this.appIdValue = appIdValue;
        this.appUrl = appUrl;
    }

    @Override
    @CircuitBreaker(name = "getCurrentWeatherReport", fallbackMethod = "getCurrentWeatherReportFallback")
    public Optional<WeatherReport> getCurrentWeatherReport() {
        URI openWeatherApiUri = UriComponentsBuilder.fromHttpUrl(appUrl)
                .queryParam(LATITUDE_QUERY_PARAM, LATITUDE_QUERY_PARAM_VALUE)
                .queryParam(LONGITUDE_QUERY_PARAM, LONGITUDE_QUERY_PARAM_VALUE)
                .queryParam(LANGUAGE_QUERY_PARAM, LANGUAGE_QUERY_PARAM_VALUE)
                .queryParam(UNITS_QUERY_PARAM, UNITS_QUERY_PARAM_VALUE)
                .queryParam(APP_ID_QUERY_PARAM, appIdValue)
                .build().toUri();

        log.info("OpenWeatherApi Uri: {}.", URISanitizerUtil.sanitize(openWeatherApiUri));

        CurrentWeatherReportResponseDto currentWeatherReportResponseDto = restClient.get().uri(openWeatherApiUri).retrieve().body(CurrentWeatherReportResponseDto.class);
        log.info("Current Weather Report obtained from OpenWeatherApi: {}.", currentWeatherReportResponseDto);

        return Optional.of(generateWeatherReportFrom(currentWeatherReportResponseDto));
    }

    private WeatherReport generateWeatherReportFrom(CurrentWeatherReportResponseDto currentWeatherReportResponseDto) {
        return new WeatherReport(
                currentWeatherReportResponseDto.getTemperatureReportEmbeddedResponseDto().getWeatherTemperature(),
                currentWeatherReportResponseDto.getCityName()
        );
    }

    public Optional<WeatherReport> getCurrentWeatherReportFallback(Throwable throwable) {
        log.error("Circuit Breaker is OPEN due to an error while getting Current Weather Report from OpenWeatherAPI: {}.", throwable.getMessage());
        return Optional.empty();
    }
}
