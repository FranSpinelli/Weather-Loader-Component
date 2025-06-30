package ar.edu.unq.weather_loader_component.application.exceptions;

public class RateLimiterException extends RuntimeException {
    public RateLimiterException(String message) {
        super(message);
    }
}
