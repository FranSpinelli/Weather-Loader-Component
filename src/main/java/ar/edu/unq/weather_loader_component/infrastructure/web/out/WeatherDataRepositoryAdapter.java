package ar.edu.unq.weather_loader_component.infrastructure.web.out;

import ar.edu.unq.weather_loader_component.domain.port.out.WeatherDataRepositoryPort;
import ar.edu.unq.weather_loader_component.infrastructure.web.out.dto.WeatherDataResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WeatherDataRepositoryAdapter implements WeatherDataRepositoryPort {

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

    public WeatherDataRepositoryAdapter(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public WeatherDataResponseDto getCurrentWeatherData() {
        URI weatherMapUri = UriComponentsBuilder.fromHttpUrl(WEATHER_MAP_URL)
                .queryParam(LATITUDE_QUERY_PARAM, LATITUDE_QUERY_PARAM_VALUE)
                .queryParam(LONGITUDE_QUERY_PARAM, LONGITUDE_QUERY_PARAM_VALUE)
                .queryParam(LANGUAGE_QUERY_PARAM, LANGUAGE_QUERY_PARAM_VALUE)
                .queryParam(UNITS_QUERY_PARAM, UNITS_QUERY_PARAM_VALUE)
                .queryParam(APP_ID_QUERY_PARAM, "${ar.edu.unq.weather.loader.component.weather.map.api.key}")
                .build().toUri();

        return restClient.get().uri(weatherMapUri).retrieve().body(WeatherDataResponseDto.class);
    }
}
