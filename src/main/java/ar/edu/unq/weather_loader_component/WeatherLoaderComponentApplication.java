package ar.edu.unq.weather_loader_component;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class WeatherLoaderComponentApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherLoaderComponentApplication.class, args);
    }

}
